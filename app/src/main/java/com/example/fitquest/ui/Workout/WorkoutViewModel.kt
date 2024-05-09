package com.example.fitquest.ui.Workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fitquest.ui.UserInfo.FitDatabase
import kotlinx.coroutines.launch
class WorkoutViewModel (application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Workout>>
    private val workoutRepository: WorkoutRepository

    init{
        val workoutDAO = FitDatabase.getDatabase(application).WorkoutDAO()
        workoutRepository = WorkoutRepository(workoutDAO)
        readAllData = workoutRepository.readAllData
    }

    fun AddWorkout(workout: Workout) = viewModelScope.launch {
        workoutRepository.AddWorkout(workout)
    }
}