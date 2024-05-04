package com.example.fitquest.ui.Workout

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExerciseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise_table ORDER BY exerciseId ASC")
    fun ReadAllData(): LiveData<List<Exercise>>

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)
}