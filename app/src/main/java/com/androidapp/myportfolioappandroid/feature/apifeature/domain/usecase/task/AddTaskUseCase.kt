package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.task

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.TaskRepository
import jakarta.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task): AppResult<Unit> {
        return taskRepository.addTask(task)
    }
}