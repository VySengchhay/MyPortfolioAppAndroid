package com.androidapp.myportfolioappandroid.feature.apifeature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.androidapp.myportfolioappandroid.feature.apifeature.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun observeAccounts(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(taskEntity: TaskEntity)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)
}