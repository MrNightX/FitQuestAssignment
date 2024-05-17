package com.example.fitquest.ui.Workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentWorkoutChooseModeBinding

class Workout_ChooseMode : Fragment() {

    private var _binding: FragmentWorkoutChooseModeBinding? = null
    private val binding get() = _binding!!

    private var ExerciseName: String = ""
    private var ExerciseImage: Int = 0
    private var ExerciseType: String = ""
    private var TargetBody: String = ""
    private var CalorieBurned: Int = 0
    private var ExerciseInfo: String = ""
    private var imagePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutChooseModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()

        binding.buttonRecommend.setOnClickListener {
            findNavController().navigate(R.id.action_workout_ChooseMode_to_presetRoutine)
        }

        binding.buttonCustom.setOnClickListener {
            findNavController().navigate(R.id.action_workout_ChooseMode_to_fragmentList, bundle)
        }

        binding.buttonCWorkoutInject1.setOnClickListener {
            println("Injected Push Ups")
            Toast.makeText(requireContext(), "Injected Push Ups", Toast.LENGTH_SHORT).show()

            ExerciseName = "Push ups"
            imagePath = "ExercisePicture/better_pushups.png"
            ExerciseType = "Strength"
            TargetBody = "Chest"
            CalorieBurned = 100
            ExerciseInfo = "Push-up exercise is a close chain kinetic exercise which improves the " +
                    "joint proprioception, joint stability and muscle co-activation around the " +
                    "shoulder joint."

            bundle.putString("exerciseName", ExerciseName)
            bundle.putString("imgPath", imagePath)
            bundle.putString("exerciseType", ExerciseType)
            bundle.putString("targetBody", TargetBody)
            bundle.putInt("calorieBurned", CalorieBurned)
            bundle.putString("exerciseInfo", ExerciseInfo)
            //findNavController().navigate(R.id.action_workout_ChooseMode_to_perExerciseFragment, bundle)
        }

        binding.buttonCWorkoutInject2.setOnClickListener {
            println("Injected Squat")
            Toast.makeText(requireContext(), "Injected Squat", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
