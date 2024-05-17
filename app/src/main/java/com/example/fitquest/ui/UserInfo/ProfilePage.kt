package com.example.fitquest.ui.UserInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitquest.MainActivity
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentProfilePageBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfilePage : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentProfilePageBinding? = null
    private lateinit var database: DatabaseReference
    private val binding get() = _binding!!
    public var  bundle: Bundle? = null

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

        val usersRef = database.child("users")
        //mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //mUserViewModel.getUserByEmailPass("bryn@gmail.com","12345").observe(viewLifecycleOwner, Observer { user ->

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





    }


}