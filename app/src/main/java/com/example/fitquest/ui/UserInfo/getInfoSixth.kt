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
import com.example.fitquest.databinding.FragmentGetInfoSixthBinding

class getInfoSixth : Fragment() {


    private var selectedLvl : Int = -1
    private var _binding: FragmentGetInfoSixthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetInfoSixthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        val userWeight = arguments?.getFloat("userWeight")
        val userHeight = arguments?.getFloat("userHeight")
        val userGoal = arguments?.getInt("userGoal")
        Toast.makeText(context, "Selected gender: $userAge $userGender $userWeight $userHeight $userGoal", Toast.LENGTH_SHORT).show()
        binding.buttonNext6.setOnClickListener {
            val level = binding.RadioGroupLevel.checkedRadioButtonId

            selectedLvl = when(level){
                binding.radioButtonLevel1.id -> 0
                binding.radioButtonLevel2.id -> 1
                binding.radioButtonLevel3.id -> 2
                else -> {
                    Toast.makeText(requireContext(), "Please select a proficiency level", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

                val bundle = Bundle()
                Toast.makeText(context, "Selected gender: $userAge $userGender $userWeight $userHeight $userGoal $selectedLvl", Toast.LENGTH_SHORT).show()

                if (userAge != null && userGender != null && userHeight != null && userWeight != null && userGoal != null) {

                        bundle.putInt("userAge", userAge)
                        bundle.putFloat("userWeight", userWeight)
                        bundle.putInt("userGender", userGender)
                        bundle.putFloat("userHeight" , userHeight)
                        bundle.putInt("userGoal", userGoal)
                        bundle.putInt("UserLvl", selectedLvl)
                }
                findNavController().navigate(R.id.registerFragment,bundle)






        }
        binding.imageButtonBack6.setOnClickListener {
            requireFragmentManager().popBackStack()
        }
    }


}