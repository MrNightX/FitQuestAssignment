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

    fun getUserByEmail(email: String): LiveData<User> {
        return userDAO.getUserByEmail(email)
    }
    fun getUserByEmailPass(email: String,password: String): LiveData<User> {
        return userDAO.getUserByEmailPass(email,password)
    }
}