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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

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


        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        val userWeight = arguments?.getFloat("userWeight")
        val userHeight = arguments?.getFloat("userHeight")
        val userGoal = arguments?.getInt("userGoal")
        val userLvl = arguments?.getInt("userLvl")
        binding.buttonRegister.setOnClickListener {
            username = binding.editTextRegisterUserFullName.text.toString()
            phoneNum = binding.editTextRegisterPhone.text.toString()
            email = binding.editTextRegisterEmail.text.toString()
            password = binding.editTextRegisterPassword.text.toString()

            if (username.isEmpty() || phoneNum.isEmpty() || email.isEmpty() || password.isEmpty()) {
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
            }

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


