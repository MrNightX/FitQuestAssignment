package com.example.fitquest.ui.UserInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentProfilePageBinding


class ProfilePage : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentProfilePageBinding? = null
    private val binding get() = _binding!!

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
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.getUserByEmailPass("bryn@gmail.com","12345").observe(viewLifecycleOwner, Observer { user ->

            if(user != null) {
                val bundle = Bundle()
                binding.textViewUserName.text = user.username
            }else{
                binding.textViewUserName.text = "User not found"
            }

        })
    }


}