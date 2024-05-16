package com.example.fitquest.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentHomeBinding
import com.example.fitquest.ui.UserInfo.UserViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlin.math.pow

class HomeFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentHomeBinding? = null

    //private var email : String = ""
    private var bmi : Float = 0.0f
    private var heightInM : Float = 0.0f
    private var height : Float = 0.0f
    private var weight : Float = 0.0f
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var username : String = ""
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = arguments?.getString("email")
        database = FirebaseDatabase.getInstance().reference
        val usersRef = database.child("users")

        usersRef.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (userSnapshot in snapshot.children) {
                        username = userSnapshot.child("username").getValue(String::class.java)!!
                        height = userSnapshot.child("height").getValue(Float::class.java)!!
                        weight = userSnapshot.child("weight").getValue(Float::class.java)!!
                        heightInM = (height / 100).pow(2)
                        bmi = weight / heightInM



                        binding.textViewHomeUserName.text = username

                        if(bmi < 18.5)
                            binding.textViewBMIStatus.text = getString(R.string.BMI_UnderStatus)
                        else if(bmi in 18.5..24.9)
                            binding.textViewBMIStatus.text = getString(R.string.BMI_DefaultStatus)
                        else if(bmi >= 25)
                            binding.textViewBMIStatus.text = getString(R.string.BMI_OverStatus)

                        binding.textViewBMIValue.text = String.format("%.2f",bmi)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })



        binding.testButtonGetInfo.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.login_In)
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
