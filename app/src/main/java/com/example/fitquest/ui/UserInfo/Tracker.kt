package com.example.fitquest.ui.UserInfo

import androidx.room.Entity

@Entity(tableName = "tracker_table")
data class Tracker(
    val totalBurned: Int,
    val dailyGoal: Int

)
