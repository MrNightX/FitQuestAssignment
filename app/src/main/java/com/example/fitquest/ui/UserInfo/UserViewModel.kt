package com.example.fitquest.ui.UserInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class UserViewModel (application: Application): AndroidViewModel(application){
    private val readAllData: LiveData<List<User>>
    private val userRepository: UserRepository

    init{
        val userDAO = FitDatabase.getDatabase(application).UserDAO()
        userRepository = UserRepository(userDAO)
        readAllData = userRepository.readAllData
    }

    fun AddProfile(user: User) = viewModelScope.launch {
        userRepository.AddUser(user)
    }
}