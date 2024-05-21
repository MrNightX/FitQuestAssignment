package com.example.fitquest.ui.Workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Workout_table2")
data class WorkoutV2 (
    @PrimaryKey(autoGenerate = true)

    var workoutName: String = "",
    var uid : String     = "",
    var workoutDesc : String = "",
    var exerciseList: MutableList<Exercise> = mutableListOf()
)

