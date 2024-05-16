package com.example.fitquest

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fitquest.databinding.FragmentPerExerciseBinding
import com.example.fitquest.ui.UserInfo.Verification_Second
import com.google.firebase.storage.FirebaseStorage

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PerExerciseFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null



    //private lateinit var mUserViewModel : UserViewModel
    private var _binding : FragmentPerExerciseBinding? = null
    private val binding get() = _binding!!

    private var ExerciseName: String    = ""
    private var ExerciseImage : Int     = 0
    private var ExerciseType: String    = ""
    private var TargetBody: String      = ""
    private var CalorieBurned : Int     = 0
    private var ExerciseInfo : String   = ""

    private var imagePath: String = ""

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
        _binding = FragmentPerExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ONE_MEGABYTE : Long = 1024 * 1024

        binding.buttonTestExercise1.setOnClickListener {


            ExerciseName = "Push up"
            //ExerciseImage = R.drawable.better_pushups
            imagePath = "ExercisePicture/better_pushups.png"
            ExerciseType = "Strength"
            TargetBody = "Chest"
            CalorieBurned = 100
            ExerciseInfo = "Push-up exercise is a close chain kinetic exercise which improves the " +
                    "joint proprioception, joint stability and muscle co-activation around the " +
                    "shoulder joint."

            ChangeImage(imagePath)
            ChangeData()


        }

        binding.buttonTestExercise2.setOnClickListener {
            ExerciseName = "Squat"
            //ExerciseImage = R.drawable.better_squat
            imagePath = "ExercisePicture/better_squat.png"

            ExerciseType = "Strength"
            TargetBody = "Leg"
            CalorieBurned = 200
            ExerciseInfo = "A squat is a strength exercise in which the trainee lowers their hips " +
                    "from a standing position and then stands back up."

            ChangeImage(imagePath)
            ChangeData()
        }

        binding.buttonBackExercise.setOnClickListener {
            findNavController().popBackStack()
        }
        //Refer to VerificationFirst and VerificationSecond


    }

    fun ChangeData()
    {
        binding.textViewExerciseName.setText(ExerciseName)
        binding.imageViewCurrentExercise.setImageResource(ExerciseImage)
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