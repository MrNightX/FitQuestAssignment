package com.example.fitquest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitquest.databinding.FragmentSelectedPresetListBinding
import com.example.fitquest.ui.Workout.Exercise
import com.example.fitquest.ui.Workout.ExerciseAdapter
import com.google.firebase.database.FirebaseDatabase
import com.example.fitquest.ui.Workout.SharedViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match

class selected_preset_list : Fragment() {
    private lateinit var auth: FirebaseAuth
    private val viewModel: SharedViewModel by activityViewModels()

    private var _binding : FragmentSelectedPresetListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ExerciseAdapter
    private var thisExercise: MutableList<Exercise> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedPresetListBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        println("PLEASE WORK")

        //val database = FirebaseDatabase.getInstance()
        val workoutDatabase = database.getReference("Workout")
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val uid = user!!.uid

        binding.selectedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExerciseAdapter(
            thisExercise,
            onItemClick = {
                println("This worked")
            }
        )
        binding.selectedRecyclerView.adapter = adapter

        workoutDatabase.orderByChild("uid").equalTo(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    println("IF WORK")
                    var num: Int = 0
                    for (userSnapshot in snapshot.children) {
                        var tempExercise : Exercise
                        var workout_name : String = ""
                        var workout_desc : String = ""
                        workout_name = userSnapshot.child("workoutName").getValue(String::class.java).toString()
                        workout_desc = userSnapshot.child("workoutDesc").getValue(String::class.java).toString()

                        tempExercise = Exercise(
                            exerciseName = workout_name,
                            exerciseDesc = workout_desc
                        )
                        println("This worked?")
                        thisExercise.add(tempExercise)
                        //println(thisExercise[num].exerciseName)
                        num++
                        //viewModel.addExercise(tempExercise)

                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "DATABASE ERROR", Toast.LENGTH_SHORT).show()
                }


            })


    }


    private fun armsPreset(): List<Exercise>
    {
        return listOf(
            Exercise(0, "Push Up","mychest.jpg", "Strength", "", "Chest")
        )
    }

    private fun chestPreset(): List<Exercise>
    {
        return listOf(
            Exercise(0, "Push Up","mychest.jpg", "Strength", "", "Chest")
        )
    }

    private fun abdominalPreset(): List<Exercise>
    {
        return listOf(
            Exercise(0, "Sit Up","myabs.jpg", "Strength", "", "Abdominal")
        )
    }

    private fun legsPreset(): List<Exercise>
    {
        return listOf(
            Exercise(0, "Squat","myleg.jpg", "Strength", "", "Legs")
        )
    }

    private fun fullbodyPreset(): List<Exercise>
    {
        return listOf(
            Exercise(0, "Thrusting","mydddd.jpg", "Strength", "", "lower part")
        )
    }
    /*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment selected_preset_list.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            selected_preset_list().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    } */
}