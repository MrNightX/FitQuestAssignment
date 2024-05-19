package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.fitquest.MainActivity
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentProfilePageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlin.text.Regex



class ProfilePage : Fragment() {


    private var _binding: FragmentProfilePageBinding? = null
    private lateinit var database: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var storageRef: StorageReference
    private val binding get() = _binding!!
    public var  bundle: Bundle? = null
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = (requireActivity() as MainActivity).bundle
        val email = bundle?.getString("email")
        database = FirebaseDatabase.getInstance().reference
        storageRef = FirebaseStorage.getInstance().reference
        val usersRef = database.child("users")







        usersRef.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (userSnapshot in snapshot.children) {
                        binding.textViewUserName.text = userSnapshot.child("username").getValue(String::class.java)
                        var uriString = userSnapshot.child("photoUri").getValue(String::class.java)

                        if(uriString != "0") {
                            val uri = Uri.parse(uriString)
                            Picasso.get().load(uri).into(binding.imageViewUserImage)
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseRealtime", "Database Error")
                }

            })

        binding.buttonGoal.setOnClickListener {
            findNavController().navigate(R.id.goalPage)
        }
        binding.buttonMyBody.setOnClickListener {
            findNavController().navigate(R.id.myBodyPage)
        }

        binding.buttonSettings.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
        }


        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {

                binding.imageViewUserImage.setImageURI(uri)
                val user = Firebase.auth.currentUser //to get current user uid
                val fileName = user!!.uid + ".jpg"
                val imageRef = storageRef.child("UserImage/$fileName")

                imageRef.putFile(uri)
                    .addOnSuccessListener { taskSnapshot ->
                        imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            usersRef.orderByChild("email").equalTo(email)
                                .addListenerForSingleValueEvent(object : ValueEventListener{
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        for (userSnapshot in snapshot.children) {
                                            userSnapshot.ref.child("photoUri").setValue(downloadUri.toString())
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                        Log.e("FirebaseStorage", "Image upload to realtime failed")
                                    }

                                })
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.e("FirebaseStorage", "Image upload to storage failed")
                    }
            } else {
                // Handle the case where no image was selected
                Log.e("System", "No image was selected")
            }
        }

        binding.imageViewUserImage.setOnClickListener {
            pickImage.launch("image/*")
        }




    }


}