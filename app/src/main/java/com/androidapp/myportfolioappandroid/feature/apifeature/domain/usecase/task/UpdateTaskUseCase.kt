package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.task

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
     suspend operator fun invoke(task: Task): AppResult<Unit> {
        return taskRepository.updateTask(task)
    }
}