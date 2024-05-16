package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentVerificationFIrstBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Verification_FIrst.newInstance] factory method to
 * create an instance of this fragment.
 */
class Verification_FIrst : Fragment() {

    private var userGender: Int = -1
    private var _binding: FragmentVerificationFIrstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = FragmentVerificationFIrstBinding.inflate(layoutInflater)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerificationFIrstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButtonMale.setOnClickListener {
            saveUserGender(0)
            userGender = 0

        }

        binding.imageButtonFemale.setOnClickListener {
            saveUserGender(1)
            userGender = 1
        }

        binding.buttonNext.setOnClickListener {
            // Check if the user has selected a gender before proceeding
            if (userGender > -1) {
                val bundle = Bundle()


                bundle.putInt("userGender", userGender)
                // If a gender is selected, create an Intent to move to the SecondStep activity
               findNavController().navigate(R.id.action_getInfoFirst_to_getInfoSecond, bundle)
            } else {
                // If no gender is selected, display a message to prompt the user to select a gender
                Toast.makeText(requireContext(), "Please select a gender", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun saveUserGender(gender: Int) {
        // Store the user's gender in the userGender variable

        // Update the UI to reflect the selected gender
        when (gender) {
            0 -> {
                binding.imageButtonMale.isSelected = true
                binding.imageButtonFemale.isSelected = false
            }

            1 -> {
                binding.imageButtonMale.isSelected = false
                binding.imageButtonFemale.isSelected = true
            }
        }
        // Display a message indicating the selected gender
        //Toast.makeText(this, "Selected gender: $gender", Toast.LENGTH_SHORT).show()


    }

}