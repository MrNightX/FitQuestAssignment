package com.example.fitquest.ui.UserInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    val username: String,
    val gender: Int,
    val age: Int,
    val contactNum: String,
    val height: Float,
    val weight: Float,
    val questionWOGoal: Int, // 0 - 4
    val questionWOLvl: Int, // 0 - 2
    @PrimaryKey
    val email: String,
    val password: String

)
