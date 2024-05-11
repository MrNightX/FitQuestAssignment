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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [getInfoThird.newInstance] factory method to
 * create an instance of this fragment.
 */
class getInfoThird : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentGetInfoThirdBinding? = null
    private val binding get() = _binding!!
    private var userWeight: Float = 0.0f

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
        _binding = FragmentGetInfoThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAge = arguments?.getInt("userAge")
        val userGender = arguments?.getInt("userGender")
        Toast.makeText(context, "Selected gender: $userAge $userGender", Toast.LENGTH_SHORT).show()


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
                findNavController().navigate(R.id.action_getInfoThird_to_getInfoFourth, bundle)
            }
            else{
                Toast.makeText(requireContext(), "Please enter your age", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageButtonBack3.setOnClickListener {
            val intent = Intent(requireContext(), Verification_Second::class.java)
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
         * @return A new instance of fragment getInfoThird.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getInfoThird().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}