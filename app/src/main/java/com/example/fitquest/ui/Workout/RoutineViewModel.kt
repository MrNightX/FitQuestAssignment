package com.example.fitquest.ui.Workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fitquest.ui.UserInfo.FitDatabase
import kotlinx.coroutines.launch

class RoutineViewModel (application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Routine>>
    private val routineRepository: RoutineRepository

    init{
        val routineDAO = FitDatabase.getDatabase(application).RoutineDAO()
        routineRepository = RoutineRepository(routineDAO)
        readAllData = routineRepository.readAllData
    }

    fun AddRoutine(routine: Routine) = viewModelScope.launch {
        routineRepository.AddRoutine(routine)
    }
}