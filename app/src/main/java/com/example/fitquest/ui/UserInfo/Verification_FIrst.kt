package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fitquest.databinding.FragmentVerificationFIrstBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Verification_FIrst.newInstance] factory method to
 * create an instance of this fragment.
 */
class Verification_FIrst : Fragment() {

    private var userGender: String? = null
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
            saveUserGender("Male")
        }

        binding.imageButtonFemale.setOnClickListener {
            saveUserGender("Female")
        }

        binding.buttonNext.setOnClickListener {
            // Check if the user has selected a gender before proceeding
            if (userGender != null) {
                // If a gender is selected, create an Intent to move to the SecondStep activity
                val intent = Intent(requireContext(), Verification_Second::class.java)
                startActivity(intent)
            } else {
                // If no gender is selected, display a message to prompt the user to select a gender
                Toast.makeText(requireContext(), "Please select a gender", Toast.LENGTH_SHORT).show()
            }
        }

        //TODO: This is for linking Terence's page with the back button to return to registration page
        /*binding.imageButtonBack.setOnClickListener {
            val intent = Intent(this, SecondStep::class.java)
            startActivity(intent)
        }*/
    }

    private fun saveUserGender(gender: String) {
        // Store the user's gender in the userGender variable
        userGender = gender

        // Update the UI to reflect the selected gender
        when (gender) {
            "Male" -> {
                binding.imageButtonMale.isSelected = true
                binding.imageButtonFemale.isSelected = false
            }

            "Female" -> {
                binding.imageButtonMale.isSelected = false
                binding.imageButtonFemale.isSelected = true
            }
        }
        // Display a message indicating the selected gender
        //Toast.makeText(this, "Selected gender: $gender", Toast.LENGTH_SHORT).show()


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Verification_FIrst.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Verification_FIrst().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}