package com.example.fitquest.ui.Workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitquest.databinding.FragmentSelectedExerciseBinding

class SelectedExercisesFragment : Fragment() {
    private var _binding: FragmentSelectedExerciseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedExercises.observe(viewLifecycleOwner) { selectedExercises ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = ExerciseAdapter(selectedExercises) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
