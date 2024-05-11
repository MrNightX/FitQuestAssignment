package com.example.fitquest.ui.UserInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracker_table")
data class Tracker(
    @PrimaryKey
    val totalBurned: Int,
    val dailyGoal: Int

)
