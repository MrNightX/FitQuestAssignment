package com.example.fitquest.ui.UserInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitquest.MainActivity
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentGoalPageBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage


class goalPage : Fragment() {

    private var _binding : FragmentGoalPageBinding? = null
    private lateinit var database: DatabaseReference
    lateinit var storage: FirebaseStorage
    private val binding get() = _binding!!
    public var  bundle: Bundle? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGoalPageBinding.inflate(inflater, container, false)
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

                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
}