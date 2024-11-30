package com.ita.myapp.classes.data.model.database

import android.content.Context
import androidx.room.Room
import com.ita.myapp.classes.ui.screens.AppDatabase

object DatabaseProvider {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return instance ?: synchronized(this){
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase:: class.java, "app-db"
            ).build()
            instance = db
            db
        }
    }
}