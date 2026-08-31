package com.androidapp.myportfolioappandroid.feature.apifeature.data.repository

import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.local.dao.TaskDao
import com.androidapp.myportfolioappandroid.feature.apifeature.data.local.maper.toDomain
import com.androidapp.myportfolioappandroid.feature.apifeature.data.local.maper.toEntity
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.TaskRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun observeTasks(): Flow<List<Task>> {
        return flow {
            taskDao.observeAccounts().collect { tasks ->
                emit(tasks.map { it.toDomain() })
            }
        }
    }


    override suspend fun addTask(task: Task): AppResult<Unit> {
        return try {
            taskDao.insertTask(task.toEntity())
            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Error(AppError.Unknown(e))
        }
    }
}