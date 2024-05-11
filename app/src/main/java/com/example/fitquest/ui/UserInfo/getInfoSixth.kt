package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentGetInfoSixthBinding
import com.example.fitquest.ui.home.HomeFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [getInfoSixth.newInstance] factory method to
 * create an instance of this fragment.
 */
class getInfoSixth : Fragment() {
    // TODO: Rename and change types of parameters

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
                    Toast.makeText(requireContext(), "Please select a goal", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            /*
            *       "Beginner" - 0
                    "Intermediate" - 1
                    "Advanced" - 2
                    *    val bundle = Bundle()
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
                if (userGoal != null) {
                    bundle.putInt("userGoal", userGoal)
                }

                bundle.putInt("userLevel", level)*/
            if (selectedLvl != -1) {
                val bundle = Bundle()
                Toast.makeText(context, "Selected gender: $userAge $userGender $userWeight $userHeight $userGoal $selectedLvl", Toast.LENGTH_SHORT).show()
                // If a gender is selected, create an Intent to move to the SecondStep activity
                if (userAge != null && userGender != null && userHeight != null && userWeight != null && userGoal != null) {

                        bundle.putInt("userAge", userAge)
                        bundle.putFloat("userWeight", userWeight)
                        bundle.putInt("userGender", userGender)
                        bundle.putFloat("userHeight" , userHeight)
                        bundle.putInt("userGoal", userGoal)
                        bundle.putInt("UserLvl", selectedLvl)
                }
                findNavController().navigate(R.id.action_getInfoSixth_to_register,bundle)

                //TODO: Bryan just change this intent to the homepage so after user clicks the button it will move to ur page

            }
            // Handle case if no level is selected
            else {
                Toast.makeText(requireContext(), "Please select a proficiency level", Toast.LENGTH_SHORT).show()
            }


        }
        binding.imageButtonBack6.setOnClickListener {
            val intent = Intent(requireContext(), getInfoFifth::class.java)
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment getInfoSixth.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getInfoSixth().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}