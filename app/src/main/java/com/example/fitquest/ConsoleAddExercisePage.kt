package com.example.fitquest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitquest.databinding.FragmentConsoleAddExercisePageBinding
import com.example.fitquest.ui.Workout.Exercise
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsoleAddExercisePage.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsoleAddExercisePage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentConsoleAddExercisePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseRef : DatabaseReference

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
        _binding = FragmentConsoleAddExercisePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var filepath : String = ""
        //filepath += binding.editTextExerciseName.text.toString()
        //Toast.makeText(requireContext(), filepath, Toast.LENGTH_SHORT).show()

        binding.buttonAddData.setOnClickListener {

            filepath = "Exercise/"
            filepath += binding.editTextExerciseName.text.toString()
            Toast.makeText(requireContext(), filepath, Toast.LENGTH_SHORT).show()

            val exercise:Exercise = Exercise(
                exerciseId = binding.editTextExerciseID.text.toString().toInt(),
                exerciseName =  binding.editTextExerciseName.text.toString(),
                exerciseImgPath =  binding.editTextImgPath.text.toString(),
                exerciseType =  binding.editTextExerciseType.text.toString(),
                exerciseDesc =  binding.editTextExerciseDesc.text.toString(),
                targetBody =  binding.editTextTargetBody.text.toString(),
                timeSec =  0,
                weight =  2.0f,
                numOfReps =  10,
                numOfSets = 3,
                restBetweenSets =  10,
                burnedCalorie =  100
            )

            firebaseRef = FirebaseDatabase.getInstance().getReference(filepath)

            firebaseRef.setValue(exercise)
                .addOnCompleteListener {
                    Toast.makeText(requireContext(), binding.editTextExerciseName.text.toString() + " Is Set", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "This failed", Toast.LENGTH_SHORT).show()
                }
            /*
            val database = FirebaseDatabase.getInstance()
            val exerciseRef = database.getReference("Exercises")

            val gson = GsonBuilder().create()
            val json = gson.toJson(exercise)

            exerciseRef.push().setValue(json)
            */

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConsoleAddExercisePage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConsoleAddExercisePage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}