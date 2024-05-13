package com.example.fitquest.ui.UserInfo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY userId ASC")
    fun ReadAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE username = :username")
    fun getUserByUsername(username: String): LiveData<User>

    @Query("SELECT * FROM user_table WHERE email = :email")
    fun getUserByEmail(email: String): LiveData<User>
    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    fun getUserByEmailPass(email: String,password :String): LiveData<User>

    @Update
    suspend fun updateProfile(user: User)

    @Delete
    suspend fun deleteProfile(user: User)
}