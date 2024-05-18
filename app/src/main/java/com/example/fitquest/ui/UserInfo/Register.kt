package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentRegisterBinding
import com.example.fitquest.ui.Workout.Exercise
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Register : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private var username : String = ""
    private var password : String = ""
    private var phoneNum : String = ""
    private var email : String = ""

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()


        binding.buttonRegister.setOnClickListener {

            username = binding.editTextRegisterUserFullName.text.toString()
            phoneNum = binding.editTextRegisterPhone.text.toString()
            email = binding.editTextRegisterEmail.text.toString()
            password = binding.editTextRegisterPassword.text.toString()

            if (username.isEmpty() || phoneNum.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Log.d("Email Fail","Email Wrong")
                return@setOnClickListener
            }


            val usersRef = database.child("users")
            usersRef.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(requireContext(), "Email already exists", Toast.LENGTH_SHORT).show()
                        } else {
                            registerUser(email, password)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(requireContext(), "Canceled", Toast.LENGTH_SHORT).show()
                    }
                })

        }

        binding.textViewToLogin.setOnClickListener {
            val intent = Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
        }

    }


    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser //to get current user uid
                    if (user != null) {
                        saveUserToDatabase(user.uid)
                    }
                } else {
                    Toast.makeText(
                        activity,
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveUserToDatabase(uid: String) {
        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        val userWeight = arguments?.getFloat("userWeight")
        val userHeight = arguments?.getFloat("userHeight")
        val userGoal = arguments?.getInt("userGoal")
        val userLvl = arguments?.getInt("userLvl")

        val user:User = User(username,userGender!!,userAge!!,phoneNum, userHeight!!,userWeight!!,userGoal!!,userLvl!!,email,password,uid)

        val usersRef = database.child("users")
        usersRef.child(uid).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "User is Created", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "User Fail to create", Toast.LENGTH_SHORT).show()
            }
    }


}


