package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentVerificationSecondBinding


class Verification_Second : Fragment() {

    private var userAge: Int = 0

    private var _binding: FragmentVerificationSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerificationSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userGender = arguments?.getInt("userGender")
        Toast.makeText(context, "Selected gender: $userGender", Toast.LENGTH_SHORT).show()


                binding.buttonNext2.setOnClickListener {
                    userAge = binding.editTextNumberAge.text.toString().toInt()
                    if (userAge > 0) { // Check if userAge is greater than 0
                        val bundle = Bundle()
                        bundle.putInt("userAge", userAge)
                        if (userGender != null) {
                            bundle.putInt("userGender", userGender)
                        }
                        // If a gender is selected, create an Intent to move to the SecondStep activity
                        findNavController().navigate(R.id.getInfoThird, bundle)
                    }
                    else{
                        Toast.makeText(requireContext(), "Please enter your age", Toast.LENGTH_SHORT).show()
                    }
                }


        binding.imageButtonBack2.setOnClickListener {
            requireFragmentManager().popBackStack()
        }
    }


}