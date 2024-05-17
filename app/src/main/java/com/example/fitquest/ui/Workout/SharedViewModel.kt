package com.example.fitquest.ui.Workout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedExercises = MutableLiveData<MutableList<Exercise>>(mutableListOf())
    val selectedExercises: LiveData<MutableList<Exercise>> get() = _selectedExercises

    fun addExercise(exercise: Exercise) {
        _selectedExercises.value?.add(exercise)
        _selectedExercises.value = _selectedExercises.value
    }
}
