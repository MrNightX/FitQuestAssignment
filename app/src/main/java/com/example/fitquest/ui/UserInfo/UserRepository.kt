package com.example.fitquest.ui.UserInfo

import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {

    val readAllData: LiveData<List<User>> = userDAO.ReadAllData()

    suspend fun AddUser(user: User){
        userDAO.AddUser(user)
    }

    fun getUserByUsername(username: String): LiveData<User> {
        return userDAO.getUserByUsername(username)
    }
}