package com.example.fitquest.ui.UserInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    var username: String,
    var gender: Int,
    var age: Int,
    var contactNum: String,
    var height: Float,
    var weight: Float,
    var questionWOGoal: Int, // 0 - 4
    var questionWOLvl: Int, // 0 - 2
    @PrimaryKey
    var email: String,
    var password: String,
    var uid: String

)
