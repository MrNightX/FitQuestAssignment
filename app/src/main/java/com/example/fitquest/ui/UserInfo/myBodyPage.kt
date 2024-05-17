package com.example.fitquest.ui.UserInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fitquest.MainActivity
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentMyBodyPageBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage


class myBodyPage : Fragment() {
    private var _binding : FragmentMyBodyPageBinding? = null
    private lateinit var database: DatabaseReference
    lateinit var storage: FirebaseStorage
    private val binding get() = _binding!!
    public var  bundle: Bundle? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyBodyPageBinding.inflate(inflater, container, false)
        return binding.root
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
                        //Logic Here
                        binding.textViewPreviousHeight.text = userSnapshot.child("height").getValue(Float::class.java).toString()
                        binding.textViewPreviousWeight.text = userSnapshot.child("weight").getValue(Float::class.java).toString()
                        binding.buttonChange.setOnClickListener {
                            var newHeight : String = ""
                            var newWeight : String = ""
                            newHeight = binding.editTextNumberCurrentHeight.text.toString()
                            newWeight = binding.editTextNumberCurrentWeight.text.toString()

                            if(newHeight.isEmpty()){
                                Toast.makeText(requireContext(),"Please enter your new height",Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                            if(newWeight.isEmpty()){
                                Toast.makeText(requireContext(),"Please enter your new Weight",Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                            userSnapshot.ref.child("height").setValue(newHeight.toFloat())
                            userSnapshot.ref.child("weight").setValue(newWeight.toFloat())
                            findNavController().navigate(R.id.profilePage)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        binding.buttonBackBody.setOnClickListener {
            requireFragmentManager().popBackStack()
        }

    }

    private fun UpdateBody(email: String){

    }

}