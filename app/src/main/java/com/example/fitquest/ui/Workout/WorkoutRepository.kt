package com.example.fitquest.ui.Workout

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDAO: WorkoutDAO) {

    val readAllData: LiveData<List<Workout>> = workoutDAO.ReadAllData()

    suspend fun AddWorkout(workout: Workout){
        workoutDAO.AddWorkout(workout)
    }
}