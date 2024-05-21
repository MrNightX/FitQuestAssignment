package com.example.fitquest.ui.Workout

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int         = 0,
    val exerciseName: String    = "",
    val exerciseImgPath : String= "",
    val exerciseType : String   = "",
    val exerciseDesc: String    = "",
    val targetBody: String      = "",
    val timeSec: Int            = 0,
    val weight: Float           = 0f,
    val numOfReps: Int          = 1,
    val numOfSets: Int          = 3,
    val restBetweenSets: Int    = 60,
    val burnedCalorie: Int      = 100,
)

