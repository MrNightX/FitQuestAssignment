package com.example.fitquest.ui.Workout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitquest.databinding.ItemExerciseBinding

class ExerciseAdapter(
    private val exercises: MutableList<Exercise>,
    private val onItemClick: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exercises[position])
        holder.itemView.setBackgroundColor(if (selectedPosition == position) Color.LTGRAY else Color.TRANSPARENT)
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onItemClick(exercises[holder.adapterPosition])

        }

    }

    override fun getItemCount() = exercises.size

    inner class ExerciseViewHolder(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            binding.exerciseName.text = exercise.exerciseName
            binding.exerciseDesc.text = exercise.exerciseDesc
        }
    }
}
