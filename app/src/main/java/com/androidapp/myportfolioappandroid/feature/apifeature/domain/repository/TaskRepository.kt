package com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task

interface TaskRepository {
    suspend fun addTask(task: Task): AppResult<Unit>
}