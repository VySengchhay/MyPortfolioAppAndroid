package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.task

import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.observeTasks()
    }
}