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
import com.example.fitquest.databinding.FragmentGetInfoFifthBinding


class getInfoFifth : Fragment() {



    private var _binding: FragmentGetInfoFifthBinding? = null
    private val binding get() = _binding!!
    var selected_goal: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetInfoFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        val userWeight = arguments?.getFloat("userWeight")
        val userHeight = arguments?.getFloat("userHeight")

        /*
        *        "Weight Loss" - 0
                 "Muscle Gain" - 1
                 "Flexibility and Mobility" - 2
                 "Improve Cardiovascular Health" - 3
                 "Reduce Stress" - 4*/
        binding.buttonNext5.setOnClickListener {
            val goal = binding.RadioGroupGoal.checkedRadioButtonId

            selected_goal = when(goal){
                binding.radioButtonWeightLoss.id -> 0
                binding.radioButtonMuscleGain.id -> 1
                binding.radioButtonFlexMobility.id -> 2
                binding.radioButtonCardioHealth.id -> 3
                binding.radioButtonStress.id -> 4
                else -> {
                    Toast.makeText(requireContext(), "Please select a goal", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }


            Toast.makeText(context, "Selected gender: $selected_goal", Toast.LENGTH_SHORT).show()
            if (goal > -1) {
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
                if (userHeight != null) {
                    bundle.putFloat("userHeight" , userHeight)
                }
                bundle.putInt("userGoal", selected_goal)
                // If a gender is selected, create an Intent to move to the SecondStep activity
                findNavController().navigate(R.id.getInfoSixth, bundle)
            } else {
                // No radio button is selected, show a toast
                Toast.makeText(requireContext(), "Please select a goal", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageButtonBack5.setOnClickListener {
            requireFragmentManager().popBackStack()
        }
    }


}