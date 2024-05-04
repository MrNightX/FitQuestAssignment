package com.example.fitquest.ui.Workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Workout_table")
data class Workout (
    @PrimaryKey
    var workoutId: Int,
    var workoutType: Int
    //how to inherit
)
