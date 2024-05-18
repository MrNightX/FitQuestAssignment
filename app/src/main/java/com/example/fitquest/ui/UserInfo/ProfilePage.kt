package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitquest.MainActivity
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentProfilePageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage


class ProfilePage : Fragment() {


    private var _binding: FragmentProfilePageBinding? = null
    private lateinit var database: DatabaseReference
    lateinit var storage: FirebaseStorage
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
        storage = Firebase.storage
        val usersRef = database.child("users")


        usersRef.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (userSnapshot in snapshot.children) {
                        binding.textViewUserName.text = userSnapshot.child("username").getValue(String::class.java)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

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

        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.imageViewUserImage.setImageURI(it)
                if(it!=null){
                    uri = it
                }

        }

        binding.imageViewUserImage.setOnClickListener {
            pickImage.launch("Image/*")
        }




    }


}