package com.example.fitquest.ui.UserInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentHomeBinding



/**
 * A simple [Fragment] subclass.
 * Use the [Login_In.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login_In : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private var userList: LiveData<User>? = null
    var username : String = ""
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments?.getString("username").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }
}