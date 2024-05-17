package com.example.fitquest.ui.UserInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class UserViewModel (application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<User>>
    private val userRepository: UserRepository
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    init{
        val userDAO = FitDatabase.getDatabase(application).UserDAO()
        userRepository = UserRepository(userDAO)
        readAllData = userRepository.readAllData


    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun AddUser(user: User) = viewModelScope.launch {
        userRepository.AddUser(user)
    }

    fun getUserByUsername(username: String): LiveData<User> {
        return userRepository.getUserByUsername(username)
    }

    fun getUserByEmail(email: String): LiveData<User> {
        return userRepository.getUserByEmail(email)
    }

    fun getUserByEmailPass(email: String,password:String): LiveData<User> {
        return userRepository.getUserByEmailPass(email,password)
    }

}