package com.example.fitquest.ui.UserInfo

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitquest.ui.Workout.Exercise
import com.example.fitquest.ui.Workout.ExerciseDAO
import com.example.fitquest.ui.Workout.Workout
import com.example.fitquest.ui.Workout.WorkoutDAO


@Database(entities = [User::class , Workout::class ,Exercise::class, Tracker::class], version = 1, exportSchema = false)
internal abstract class FitDatabase : RoomDatabase() {
    //TODO LIST ALL THE DAO HERE
    abstract fun UserDAO(): UserDAO
    abstract fun WorkoutDAO(): WorkoutDAO
    abstract fun ExerciseDAO(): ExerciseDAO
    abstract fun TrackerDAO(): TrackerDAO

    companion object{
        @Volatile
        private var INSTANCE: FitDatabase? = null;

        fun getDatabase(context: Context): FitDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitDatabase::class.java,
                    "fit_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}