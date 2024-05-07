package com.example.fitquest.ui.UserInfo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TrackerDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddTracker(tracker: Tracker)

    @Query("SELECT * FROM tracker_table")
    fun ReadAllData(): LiveData<List<Tracker>>

    @Update
    suspend fun updateTracker(tracker: Tracker)

    @Delete
    suspend fun deleteTracker(tracker: Tracker)
}