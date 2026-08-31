package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.task

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Int): AppResult<Unit> {
        return taskRepository.deleteTask(id)
    }
}