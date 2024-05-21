package com.example.fitquest.ui.Workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_table")
data class Routine(
    @PrimaryKey(autoGenerate = true)
    var routineId: Int,
    var workoutId: Int,
    var exerciseId: Int
)
