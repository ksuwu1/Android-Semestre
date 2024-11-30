package com.ita.myapp.classes.ui.screens

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ita.myapp.classes.data.model.ServiceEntity
import com.ita.myapp.classes.data.model.dao.ServiceDao


@Database(entities = [ServiceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
}