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
import com.example.fitquest.databinding.FragmentGetInfoThirdBinding


class getInfoThird : Fragment() {

    private var _binding: FragmentGetInfoThirdBinding? = null
    private val binding get() = _binding!!
    private var userWeight: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetInfoThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")



        binding.buttonNext3.setOnClickListener {
            userWeight = binding.EditTextNumberWeight.text.toString().toFloat()
            if (userWeight > 0) { // Check if userAge is greater than 0
                val bundle = Bundle()
                if (userAge != null) {
                    bundle.putInt("userAge", userAge)
                }
                if (userGender != null) {
                    bundle.putInt("userGender", userGender)
                }
                bundle.putFloat("userWeight", userWeight)
                // If a gender is selected, create an Intent to move to the SecondStep activity
                findNavController().navigate(R.id.getInfoFourth, bundle)
            }
            else{
                Toast.makeText(requireContext(), "Please enter your age", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageButtonBack3.setOnClickListener {
            requireFragmentManager().popBackStack()
        }
    }


}