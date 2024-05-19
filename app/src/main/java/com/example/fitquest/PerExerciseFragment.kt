package com.example.fitquest

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitquest.databinding.FragmentPerExerciseBinding
import com.example.fitquest.ui.UserInfo.Verification_Second
import com.example.fitquest.ui.Workout.Exercise
import com.example.fitquest.ui.Workout.SharedViewModel
import com.google.firebase.storage.FirebaseStorage

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PerExerciseFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentPerExerciseBinding? = null
    private val binding get() = _binding!!

    private var exerciseID: Int         = 0
    private var ExerciseName: String    = ""
    private var ExerciseImage: String       = ""
    private var ExerciseType: String    = ""
    private var ExerciseInfo : String   = ""
    private var TargetBody: String      = ""
    private var timeSec: Int            = 0
    private var weight: Float           = 0f
    private var numOfReps: Int          = 0
    private var numOfSets: Int          = 0
    private var restBetweenSets: Int    = 0
    private var CalorieBurned : Int     = 0




    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        /*

        DON'T EVER ATTEMPT TO PERFORM SAVE STATE

         */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPerExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = arguments?.getString("exerciseName").toString()

        //viewModel.addExercise(tempExercise)

        val ONE_MEGABYTE : Long = 1024 * 1024
        exerciseID = arguments?.getInt("exerciseID").toString().toInt()
        ExerciseName = arguments?.getString("exerciseName").toString()
        ExerciseImage = arguments?.getString("imgPath").toString()
        ExerciseType = arguments?.getString("exerciseType").toString()
        ExerciseInfo = arguments?.getString("exerciseDesc").toString()
        TargetBody = arguments?.getString("targetBody").toString()
        timeSec = arguments?.getInt("timeSec")!!
        weight = arguments?.getFloat("weight")!!
        numOfReps = arguments?.getInt("numOfReps").toString().toInt()
        numOfSets = arguments?.getInt("numOfSets")!!
        restBetweenSets = arguments?.getInt("restBetweenSets")!!
        CalorieBurned = arguments?.getInt("calorieBurned")!!

        ChangeImage(ExerciseImage)
        ChangeData()

        binding.buttonRemove.setOnClickListener {
            val tempExercise : Exercise = Exercise(
                exerciseId = exerciseID,
                exerciseName = ExerciseName,
                exerciseImgPath = ExerciseImage,
                exerciseType = ExerciseType,
                exerciseDesc = ExerciseInfo,
                targetBody = TargetBody,
                timeSec = timeSec,
                weight = weight,
                numOfReps = numOfReps,
                numOfSets = numOfSets,
                restBetweenSets = restBetweenSets,
                burnedCalorie = CalorieBurned
            )
            viewModel.addExercise(tempExercise)
            println(tempExercise.exerciseName)
            println("added to list")

            findNavController().navigate(R.id.action_perExerciseFragment_to_exerciseListFragment2)
            /*
            *   Smart Function doesn't WORK
            * */
        }
        binding.buttonTestExercise1.setOnClickListener {


            ExerciseName = "Push up"
            //ExerciseImage = R.drawable.better_pushups
            ExerciseImage = "ExercisePicture/better_pushups.png"
            ExerciseType = "Strength"
            TargetBody = "Chest"
            CalorieBurned = 100
            ExerciseInfo = "Push-up exercise is a close chain kinetic exercise which improves the " +
                    "joint proprioception, joint stability and muscle co-activation around the " +
                    "shoulder joint."

            ChangeImage(ExerciseImage)
            ChangeData()


        }

        binding.buttonTestExercise2.setOnClickListener {
            ExerciseName = "Squat"
            //ExerciseImage = R.drawable.better_squat
            ExerciseImage = "ExercisePicture/better_squat.png"

            ExerciseType = "Strength"
            TargetBody = "Leg"
            CalorieBurned = 200
            ExerciseInfo = "A squat is a strength exercise in which the trainee lowers their hips " +
                    "from a standing position and then stands back up."

            ChangeImage(ExerciseImage)
            ChangeData()
        }

        binding.buttonBackExercise.setOnClickListener {
            findNavController().navigateUp()
        }
        //Refer to VerificationFirst and VerificationSecond

    }

    /*

        DON'T EVER ATTEMPT TO PERFORM SAVE STATE

    */

    fun ChangeData()
    {
        binding.textViewExerciseName.setText(ExerciseName)
        //binding.imageViewCurrentExercise.setImageResource(ExerciseImage)
        binding.textViewTypeName.setText(ExerciseType)
        binding.textViewBodyPartName.setText(TargetBody)
        binding.textViewCalorie.text = String.format("%d Kcal", CalorieBurned)
        binding.textViewExerciseInfo.setText(ExerciseInfo)
    }

    fun ChangeImage(ImagePath: String)
    {
        val storageRef = FirebaseStorage.getInstance().getReference(ImagePath)
        storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            binding.imageViewCurrentExercise.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Log.e("FirebaseStorage", "Error downloading image")
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Verification_Second.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Verification_Second().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}