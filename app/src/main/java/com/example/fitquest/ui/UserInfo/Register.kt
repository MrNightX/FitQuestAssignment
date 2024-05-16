package com.example.fitquest.ui.UserInfo

import android.os.Bundle
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


        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        val userWeight = arguments?.getFloat("userWeight")
        val userHeight = arguments?.getFloat("userHeight")
        val userGoal = arguments?.getInt("userGoal")
        val userLvl = arguments?.getInt("userLvl")
        binding.buttonRegister.setOnClickListener {
            val userId = database.push().key
            username = binding.editTextRegisterUserFullName.text.toString()
            phoneNum = binding.editTextRegisterPhone.text.toString()
            email = binding.editTextRegisterEmail.text.toString()
            password = binding.editTextRegisterPassword.text.toString()
            val user:User = User("Jason",0,25,"0129889166", 161.0f,45.0f,0,0,email,password)
            val usersRef = database.child("users")


            usersRef.orderByChild("email").equalTo(user.email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Email already exists, show error message or handle accordingly
                            // For example, you can display a Toast message
                            Toast.makeText(requireContext(), "Email already exists", Toast.LENGTH_SHORT).show()
                        } else {
                            // Email doesn't exist, proceed to save the user

                            usersRef.child(userId!!).setValue(user)
                                .addOnSuccessListener {

                                    Toast.makeText(requireContext(),"Created",Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(requireContext(),"Fail",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle errors
                        Toast.makeText(requireContext(),"Canceled",Toast.LENGTH_SHORT).show()
                    }
                })

            /*if (username.isEmpty() || phoneNum.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            if (CheckEmail(email)) {
                if (userAge != null && userGender != null && userHeight != null && userWeight != null && userGoal != null && userLvl != null) {

                            val user = User(
                                username = username,
                                password = password,
                                email = email,
                                gender = userGender,
                                age = userAge,
                                contactNum = phoneNum,
                                height = userHeight,
                                weight = userWeight,
                                questionWOGoal = userGoal,
                                questionWOLvl = userLvl
                            )
                            mUserViewModel.AddUser(user)
                            val bundle = Bundle()
                            bundle.putString("username", username)
                            findNavController().navigate(
                                R.id.action_register_to_navigation_home,
                                bundle
                            )


                }
            }else{
                Toast.makeText(requireContext(), "Email Existed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/

            }
        }

        fun  CheckEmail(email: String): Boolean{
            //Toast.makeText(requireContext(), "Email Existed", Toast.LENGTH_SHORT).show()

            var NoExist : Boolean = true


            /*mUserViewModel.getUserByEmail(email).observe(viewLifecycleOwner, Observer { user ->

                if(user != null) {
                    if(user.email == email){
                        Toast.makeText(requireContext(), "Email Found", Toast.LENGTH_SHORT).show()
                        NoExist = false
                        return@Observer

                    }
                }
            })*/

            return NoExist
        }


    }


