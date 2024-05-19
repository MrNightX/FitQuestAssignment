package com.example.fitquest.ui.Workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentExerciseListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class ExerciseListFragment : Fragment() {
    private var _binding: FragmentExerciseListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()
    private val exerciseRepository = ExerciseRepos()
    val NewList : MutableList<Exercise> = mutableListOf()
    private lateinit var adapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        fetchAllExercise()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercises = generateExerciseList()


        adapter = ExerciseAdapter(
            //exercises,
            NewList,
            onItemClick = { exercise ->
                Toast.makeText(context, "Selected: ${exercise.exerciseName}", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                //fetchExerciseData(exercise.exerciseName, bundle)
                viewModel.addExercise(exercise) // Add exercise to the shared ViewModel
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        // Set up the click listener for the button
        binding.buttonViewSelected.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_to_selectedExercisesFragment)
        }
    }

    private fun fetchExerciseData(inputName:String, bundle: Bundle)
    {
        println("Reached Here")
        val database = FirebaseDatabase.getInstance()
        val exerciseRef = database.getReference("Exercise")

        exerciseRef.orderByChild("exerciseName").equalTo(inputName).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        //If found
                        //Can be found by normal means, now how to get it
                        println("work on existence")
                        for(exerciseSnap in snapshot.children)
                        {
                            println("work on finding children")
                            val tempExercise = exerciseSnap.getValue(Exercise::class.java)
                            println("can get")
                            if (tempExercise != null) {

                                bundle.putString("exerciseName", tempExercise.exerciseName)
                                bundle.putString("imgPath", tempExercise.exerciseImgPath)
                                bundle.putString("exerciseType", tempExercise.exerciseType)
                                bundle.putString("targetBody", tempExercise.targetBody)
                                bundle.putInt("calorieBurned", tempExercise.burnedCalorie)
                                bundle.putString("exerciseInfo", tempExercise.exerciseDesc)
                                println("DATA SETTT !!!")
                                findNavController().navigate(R.id.action_workout_ChooseMode_to_perExerciseFragment, bundle)
                                //THIS WORKS FCK YEA
                            }
                        }
                    }
                    else{
                        //If not found
                        Toast.makeText(requireContext(), "Slight Error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Major Error", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    class ExerciseRepos {
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val exerciseRef: DatabaseReference = database.getReference("Exercise")

        fun fetchExercise(callback: (List<Exercise>) -> Unit) {

            exerciseRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    val exercise = task.result?.children?.mapNotNull { snapshot ->
                        snapshot.getValue(Exercise::class.java)
                    }.orEmpty()
                    callback(exercise)
                }
                else
                {
                    callback(emptyList())
                }
            }
        }
    }

    private fun fetchAllExercise() {

        lifecycleScope.launch {
        exerciseRepository.fetchExercise { exercises ->
                NewList.clear()
                NewList.addAll(exercises)

                adapter.notifyDataSetChanged() //Notify their parents :D
                println("It Worked")
            }
        }
    }

    private fun generateExerciseList(): List<Exercise> {
        return listOf(

            Exercise(0, "Push-up", "","", "A basic push-up", "Chest", 60, 0f, 10, 3, 60, 50),
            Exercise(0, "Squat", "","","A basic squat", "Legs", 60, 0f, 10, 3, 60, 50),
            Exercise(0, "Plank", "","","A basic plank", "Core", 60, 0f, 10, 3, 60, 50),
            Exercise(0, "Lunge", "","","A basic lunge", "Legs", 60, 0f, 10, 3, 60, 50),
            Exercise(0, "Burpee", "","","A basic burpee", "Full Body", 60, 0f, 10, 3, 60, 50),
            Exercise(0, "Sit-up", "","","A basic sit-up", "Abs", 60, 0f, 15, 3, 60, 30),
            Exercise(0, "Pull-up","", "","A basic pull-up", "Back", 60, 0f, 8, 3, 60, 70),
            Exercise(0, "Deadlift","", "","A basic deadlift", "Back and Legs", 60, 100f, 5, 3, 90, 100),
            Exercise(0, "Bench Press", "","","A basic bench press", "Chest", 60, 80f, 8, 3, 90, 80),
            Exercise(0, "Bicep Curl", "","","A basic bicep curl", "Arms", 60, 15f, 12, 3, 60, 40),
            Exercise(0, "Tricep Dip", "","","A basic tricep dip", "Arms", 60, 0f, 10, 3, 60, 50),
            Exercise(0, "Leg Press", "","","A basic leg press", "Legs", 60, 100f, 8, 3, 90, 80),
            Exercise(0, "Shoulder Press","", "","A basic shoulder press", "Shoulders", 60, 20f, 10, 3, 60, 60),
            Exercise(0, "Calf Raise", "","","A basic calf raise", "Legs", 60, 0f, 15, 3, 60, 30),
            Exercise(0, "Mountain Climber","", "","A basic mountain climber", "Full Body", 60, 0f, 20, 3, 60, 50),
            Exercise(0, "Russian Twist", "","","A basic Russian twist", "Abs", 60, 0f, 20, 3, 60, 30),
            Exercise(0, "Jumping Jack","", "","A basic jumping jack", "Full Body", 60, 0f, 30, 3, 60, 20)

        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
