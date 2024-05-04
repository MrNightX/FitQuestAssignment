package com.example.fitquest.ui.Workout

import androidx.lifecycle.LiveData
class ExerciseRepository(private val exerciseDAO: ExerciseDAO) {

    val readAllData: LiveData<List<Exercise>> = exerciseDAO.ReadAllData()

    suspend fun AddExercise(exercise: Exercise){

        exerciseDAO.AddExercise(exercise)
    }
}