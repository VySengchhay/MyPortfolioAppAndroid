package com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun observeTasks(): Flow<List<Task>>
    suspend fun addTask(task: Task): AppResult<Unit>

    suspend fun updateTask(task: Task): AppResult<Unit>

    suspend fun deleteTask(id: Int): AppResult<Unit>
}