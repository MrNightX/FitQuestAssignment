package com.example.fitquest.ui.Workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentWorkoutPageBinding


class WorkoutPage : Fragment() {
    private var _binding: FragmentWorkoutPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonToChoose.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_addRoutine_to_workout_ChooseMode)
        }

        binding.buttonToAddPage.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_addRoutine_to_consoleAddExercisePage)
        }
    }

}