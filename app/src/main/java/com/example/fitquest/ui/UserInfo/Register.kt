package com.example.fitquest.ui.UserInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentRegisterBinding


class Register : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var username : String? = null
    private var password : String? = null
    private var phoneNum : String? = null
    private var email : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        val userWeight = arguments?.getFloat("userWeight")
        val userHeight = arguments?.getFloat("userHeight")
        val userGoal = arguments?.getInt("userGoal")
        val userLvl = arguments?.getInt("userLvl")
        binding.buttonnRegister.setOnClickListener{
            username = binding.editTextRegisterUserFullName.text.toString()
            phoneNum = binding.editTextRegisterPhone.text.toString()
            email = binding.editTextRegisterEmail.text.toString()
            password = binding.editTextRegisterPassword.text.toString()

            if(userAge != null && userGender != null && userHeight != null && userWeight != null && userGoal != null && userLvl != null){
                when {
                    username!!.isEmpty() -> {
                        return@setOnClickListener
                    }
                    phoneNum!!.isEmpty() -> {
                        return@setOnClickListener
                    }
                    email!!.isEmpty() -> {
                        return@setOnClickListener
                    }
                    password!!.isEmpty() -> {
                        return@setOnClickListener
                    }
                    else -> {
                        val user = User(
                            username = username!!,
                            password = password!!,
                            email = email!!,
                            gender = userGender,
                            age = userAge,
                            contactNum = phoneNum!!,
                            height = userHeight,
                            weight = userWeight,
                            questionWOGoal = userGoal,
                            questionWOLvl = userLvl
                        )
                        mUserViewModel.AddUser(user)
                        val bundle = Bundle()
                        bundle.putString("username", username)
                        Toast.makeText(context, "Selected gender: $username", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_register_to_navigation_home,bundle)
                    }
                    }
                }
            }
        }
    }

