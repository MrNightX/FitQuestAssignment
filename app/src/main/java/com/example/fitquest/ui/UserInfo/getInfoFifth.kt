package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fitquest.databinding.FragmentGetInfoFifthBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [getInfoFifth.newInstance] factory method to
 * create an instance of this fragment.
 */
class getInfoFifth : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentGetInfoFifthBinding? = null
    private val binding get() = _binding!!
    var selected_goal: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

        binding.buttonNext5.setOnClickListener {
            val goal = binding.RadioGroupGoal.checkedRadioButtonId
            if (goal != -1) {
                // At least one radio button is selected
                selected_goal = when (goal) {
                    binding.radioButtonWeightLoss.id -> "Weight Loss"
                    binding.radioButtonMuscleGain.id -> "Muscle Gain"
                    binding.radioButtonFlexMobility.id -> "Flexibility and Mobility"
                    binding.radioButtonCardioHealth.id -> "Improve Cardiovascular Health"
                    binding.radioButtonStress.id -> "Reduce Stress"
                    else -> ""
                }

                val intent = Intent(requireContext(), getInfoSixth::class.java)
                startActivity(intent)
            } else {
                // No radio button is selected, show a toast
                Toast.makeText(requireContext(), "Please select a goal", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageButtonBack5.setOnClickListener {
            val intent = Intent(requireContext(), getInfoFourth::class.java)
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
         * @return A new instance of fragment getInfoFifth.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getInfoFifth().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}