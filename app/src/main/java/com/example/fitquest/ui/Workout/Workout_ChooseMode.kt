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
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue


class Workout_ChooseMode : Fragment() {

    private var _binding: FragmentWorkoutChooseModeBinding? = null
    private val binding get() = _binding!!

    private var ExerciseName: String    = ""
    private var ExerciseImage : Int     = 0
    private var ExerciseType: String    = ""
    private var TargetBody: String      = ""
    private var CalorieBurned : Int     = 0
    private var ExerciseInfo : String   = ""

    private var imagePath: String = ""

    //Testing Database
    private lateinit var firebaseRef : DatabaseReference //Reference to the database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutChooseModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()

        binding.buttonRecommend.setOnClickListener{
            Toast.makeText(requireContext(), "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        binding.buttonCustom.setOnClickListener {
            findNavController().navigate(R.id.action_workout_ChooseMode_to_perExerciseFragment, bundle)
        }

        binding.buttonCWorkoutInject1.setOnClickListener {
            println("Injected Push Ups")
            Toast.makeText(requireContext(), "Injected Push Ups", Toast.LENGTH_SHORT).show()

            ExerciseName    = "Push ups"
            imagePath       = "ExercisePicture/better_pushups.png"
            ExerciseType    = "Strength"
            TargetBody      = "Chest"
            CalorieBurned   = 100
            ExerciseInfo    = "Push-up exercise is a close chain kinetic exercise which improves the " +
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

            Toast.makeText(requireContext(), "Injected Squat", Toast.LENGTH_SHORT).show()

            //Inject data by class
            val exercise:Exercise = Exercise(1,
                "Push Up",
                "ExercisePicture/better_pushups.png" ,
                "Strength",
                "Push-up exercise is a close chain kinetic exercise which improves the " +
                        "joint proprioception, joint stability and muscle co-activation around the " +
                        "shoulder joint.",
                "Chest",
                0,
                0.0f,
                10,
                3,
                10,
                100)

            bundle.putString("exerciseName", exercise.exerciseName)
            bundle.putString("imgPath", exercise.exerciseImgPath)
            bundle.putString("exerciseType", exercise.exerciseType)
            bundle.putString("targetBody", exercise.targetBody)
            bundle.putInt("calorieBurned", exercise.burnedCalorie)
            bundle.putString("exerciseInfo", exercise.exerciseDesc)

            firebaseRef = FirebaseDatabase.getInstance().getReference("Exercise/PushUp")

            firebaseRef.setValue(exercise)
                .addOnCompleteListener {
                    Toast.makeText(requireContext(), "Pushup set", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "This failed", Toast.LENGTH_SHORT).show()
                }
        }
        binding.buttonLoadAndCheck.setOnClickListener {

            val exerciseName : String = "Push Up"

            fetchExerciseData(exerciseName)
            //Then go to the Per Exercise Fragment
            //findNavController().navigate(R.id.action_workout_ChooseMode_to_perExerciseFragment, bundle)
        }
    }

    private fun fetchExerciseData(inputName:String)
    {
        println("Reached Here")
        firebaseRef = FirebaseDatabase.getInstance().reference
        val exerciseRef = firebaseRef.child("Exercise").child(inputName)
        exerciseRef.get().addOnSuccessListener {
            Toast.makeText(requireContext(), "Can fetch data",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Can't fetch data",Toast.LENGTH_SHORT).show()
        }

        exerciseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val exercise: Exercise = snapshot.getValue(Exercise ::class.java)!!
                if(exercise != null)
                {
                    Toast.makeText(requireContext(), "Can fetch data using snapshot",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(requireContext(), "Can't fetch data using snapshot",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Error with fetching data using snapshot",Toast.LENGTH_SHORT).show()
            }

        })
    }


}