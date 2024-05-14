package com.example.fitquest.ui.Workout

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Preset_table")
data class PresetWorkout(
    @PrimaryKey(autoGenerate = true)
    val PresetID: Int,
    val exerciseID : ExerciseDAO,
    val exerciseName : String,
    val RoutineGoal: String,
    val RoutineLevel: String,
    val RestBetweenTime: Int
)
