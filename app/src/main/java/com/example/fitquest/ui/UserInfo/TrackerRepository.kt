package com.example.fitquest.ui.UserInfo

import androidx.lifecycle.LiveData

class TrackerRepository(private val trackerDAO: TrackerDAO) {

    val readAllData: LiveData<List<Tracker>> = trackerDAO.ReadAllData()

    suspend fun AddTracker(tracker: Tracker){
        trackerDAO.AddTracker(tracker)
    }
}