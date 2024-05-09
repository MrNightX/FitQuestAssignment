package com.example.fitquest.ui.Workout

import androidx.lifecycle.LiveData

class RoutineRepository (private val routineDAO: RoutineDAO) {

    val readAllData: LiveData<List<Routine>> = routineDAO.ReadAllData()

    suspend fun AddRoutine(routine: Routine){

        routineDAO.AddRoutine(routine)
    }
}