package com.example.fitquest.ui.Workout

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoutineDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddRoutine(routine: Routine)

    @Query("SELECT * FROM routine_table ORDER BY routineId ASC")
    fun ReadAllData(): LiveData<List<Routine>>

    @Update
    suspend fun updateRoutine(routine: Routine)

    @Delete
    suspend fun deleteRoutine(routine: Routine)
}