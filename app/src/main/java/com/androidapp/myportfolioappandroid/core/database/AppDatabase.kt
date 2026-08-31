package com.androidapp.myportfolioappandroid.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidapp.myportfolioappandroid.feature.apifeature.data.local.dao.TaskDao
import com.androidapp.myportfolioappandroid.feature.apifeature.data.local.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}