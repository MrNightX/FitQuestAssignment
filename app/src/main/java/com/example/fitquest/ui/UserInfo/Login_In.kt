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
import com.example.fitquest.databinding.FragmentLoginInBinding



/**
 * A simple [Fragment] subclass.
 * Use the [Login_In.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login_In : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    var email : String = ""
    var password : String = ""
    private var _binding: FragmentLoginInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        email = arguments?.getString("username").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginInBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {

            email = binding.editTextEmail.text.toString()
            password = binding.editTextPassword.text.toString()

            mUserViewModel.getUserByEmailPass(email,password).observe(viewLifecycleOwner, Observer { user ->

                if(user != null) {
                    val bundle = Bundle()
                    bundle.putString("username", user.username)
                    findNavController().navigate(R.id.action_login_In_to_navigation_home, bundle)
                }else{
                    Toast.makeText(requireContext(), "Wrong email or password", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}