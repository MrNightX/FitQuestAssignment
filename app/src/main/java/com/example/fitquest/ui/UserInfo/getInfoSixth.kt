package com.example.fitquest.ui.UserInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentGetInfoSixthBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentGetInfoSixthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext6.setOnClickListener {
            val level = binding.RadioGroupLevel.checkedRadioButtonId
            if (level != -1) {
                val selectedLevel = when (level) {
                    binding.radioButtonLevel1.id -> "Beginner"
                    binding.radioButtonLevel2.id -> "Intermediate"
                    binding.radioButtonLevel3.id -> "Advanced"
                    else -> "" // Default case, should never happen
                }

                //TODO: Bryan just change this intent to the homepage so after user clicks the button it will move to ur page
                val intent = Intent(requireContext(), HomeFragment::class.java)
                startActivity(intent)

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