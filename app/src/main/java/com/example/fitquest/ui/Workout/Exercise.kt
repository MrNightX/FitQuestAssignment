package com.example.fitquest.ui.Workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int,
    val exerciseName: String,
    val exerciseDesc: String,
    val targetBody: String,
    val timeSec: Int,
    val weight: Float,
    val numOfReps: Int,
    val numOfSets: Int,
    val restBetweenSets: Int,
    val burnedCalorie: Int,
)
