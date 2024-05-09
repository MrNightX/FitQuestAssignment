package com.example.fitquest.ui.Workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fitquest.ui.UserInfo.FitDatabase
import kotlinx.coroutines.launch
class ExerciseViewModel (application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Exercise>>
    private val exerciseRepository: ExerciseRepository

    init{
        val exerciseDAO = FitDatabase.getDatabase(application).ExerciseDAO()
        exerciseRepository = ExerciseRepository(exerciseDAO)
        readAllData = exerciseRepository.readAllData
    }

    fun AddExercise(exercise: Exercise) = viewModelScope.launch {
        exerciseRepository.AddExercise(exercise)
    }
}