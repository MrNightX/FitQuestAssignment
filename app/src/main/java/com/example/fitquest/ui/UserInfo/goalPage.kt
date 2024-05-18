package com.example.fitquest.ui.UserInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fitquest.MainActivity
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentGoalPageBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage


class goalPage : Fragment() {
    private var selectedGoal : Int = -1
    private var goalString : String = ""
    private var _binding : FragmentGoalPageBinding? = null
    private lateinit var database: DatabaseReference
    lateinit var storage: FirebaseStorage
    private val binding get() = _binding!!
    public var  bundle: Bundle? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGoalPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = (requireActivity() as MainActivity).bundle
        val email = bundle?.getString("email")
        database = FirebaseDatabase.getInstance().reference
        storage = Firebase.storage
        val usersRef = database.child("users")

        usersRef.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (userSnapshot in snapshot.children) {
                        //Logic Here
                        var currentGoal =
                            userSnapshot.child("questionWOGoal").getValue(Int::class.java)
                        var goalViewString = binding.textViewCurrentGoal.text.toString()

                        goalString = when (currentGoal) {
                            0 -> "Weight Loss"
                            1 -> "Muscle Gain"
                            2 -> "Flexibility and Mobility"
                            3 -> "Improve Cardiovascular Health"
                            4 -> "Stress Reduction"
                            else -> {
                                return
                            }
                        }
                        binding.textViewCurrentGoal.text = String.format("%s %s",goalViewString, goalString)

                        binding.buttonChangeGoal.setOnClickListener {
                            val newGoal = binding.RadioGroupGoal.checkedRadioButtonId

                            selectedGoal = when(newGoal){
                                binding.radioButtonWeightLoss.id -> 0
                                binding.radioButtonMuscleGain.id -> 1
                                binding.radioButtonFlexMobility.id -> 2
                                binding.radioButtonCardioHealth.id -> 3
                                binding.radioButtonStress.id -> 4
                                else -> {
                                    Toast.makeText(requireContext(), "Please select a goal", Toast.LENGTH_SHORT).show()
                                    return@setOnClickListener
                                }
                            }
                            userSnapshot.ref.child("questionWOGoal").setValue(selectedGoal)
                            requireFragmentManager().popBackStack()

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
}