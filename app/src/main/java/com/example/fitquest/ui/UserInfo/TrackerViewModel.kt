package com.example.fitquest.ui.UserInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TrackerViewModel (application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Tracker>>
    private val trackerRepository: TrackerRepository

    init{
        val trackerDAO = FitDatabase.getDatabase(application).TrackerDAO()
        trackerRepository = TrackerRepository(trackerDAO)
        readAllData = trackerRepository.readAllData
    }

    fun AddTracker(tracker: Tracker) = viewModelScope.launch {
        trackerRepository.AddTracker(tracker)
    }
}