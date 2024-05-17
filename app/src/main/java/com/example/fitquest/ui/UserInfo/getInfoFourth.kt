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
import com.example.fitquest.databinding.FragmentGetInfoFourthBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



class getInfoFourth : Fragment() {
    // TODO: Rename and change types of parameters


    private var userHeight: Float = 0.0f
    private var _binding: FragmentGetInfoFourthBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetInfoFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        val userWeight = arguments?.getFloat("userWeight")
        Toast.makeText(context, "Selected gender: $userAge $userGender $userWeight", Toast.LENGTH_SHORT).show()




        binding.buttonNext4.setOnClickListener {
            val heightText = binding.editTextNumberHeight.text.toString()

            userHeight=heightText.toFloat()


            if (userHeight > 0) { // Check if userAge is greater than 0
                val bundle = Bundle()
                if (userAge != null) {
                    bundle.putInt("userAge", userAge)
                }
                if (userWeight != null) {
                    bundle.putFloat("userWeight", userWeight)
                }
                if (userGender != null) {
                    bundle.putInt("userGender", userGender)
                }
                bundle.putFloat("userHeight" , userHeight)
                // If a gender is selected, create an Intent to move to the SecondStep activity
                findNavController().navigate(R.id.getInfoFifth, bundle)
            }
            else{
                Toast.makeText(requireContext(), "Please enter your height", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageButtonBack4.setOnClickListener {
            requireFragmentManager().popBackStack()
        }
    }

}