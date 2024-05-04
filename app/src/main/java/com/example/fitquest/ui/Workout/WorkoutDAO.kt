package com.example.fitquest.ui.Workout

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddWorkout(workout: Workout)

    @Query("SELECT * FROM workout_table ORDER BY workoutId ASC")
    fun ReadAllData(): LiveData<List<Workout>>

    @Update
    suspend fun updateProfile(workout: Workout)

    @Delete
    suspend fun deleteProfile(workout: Workout)
}