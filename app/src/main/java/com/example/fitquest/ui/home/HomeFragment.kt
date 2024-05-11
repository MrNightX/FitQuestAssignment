package com.example.fitquest.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentHomeBinding
import com.example.fitquest.ui.UserInfo.UserViewModel
import kotlin.math.pow

class HomeFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentHomeBinding? = null
    private var username : String = ""
    private var bmi : Float = 0.0f
    private var heightInM : Float = 0.0f
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        username = arguments?.getString("username").toString()

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.getUserByUsername("Bryan Ang").observe(viewLifecycleOwner, Observer { user ->
             heightInM = (user.height / 100).pow(2)
            bmi = user.weight / (heightInM)

            if(bmi < 18.5)
                binding.textViewBMIStatus.text = getString(R.string.BMI_UnderStatus)
            else if(bmi in 18.5..24.9)
                binding.textViewBMIStatus.text = getString(R.string.BMI_DefaultStatus)
            else if(bmi >= 25)
                binding.textViewBMIStatus.text = getString(R.string.BMI_OverStatus)

            binding.textViewHomeUserName.text = user.username
            binding.textViewBMIValue.text = String.format("%.2f", bmi)
        })


        val navController = findNavController()

        binding.testButtonGetInfo.setOnClickListener {
            navController.navigate(R.id.getInfoFirst)
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
