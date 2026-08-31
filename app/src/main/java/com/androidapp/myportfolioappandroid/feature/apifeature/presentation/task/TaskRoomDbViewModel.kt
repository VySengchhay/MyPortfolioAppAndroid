package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.core.common.toMessage
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.task.AddTaskUseCase
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.task.ObserveTasksUseCase
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.task.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskRoomDbViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    private val _observeTasksUiState = MutableStateFlow<BaseUiState<List<Task>>?>(null)
    val observeTasksUiState: StateFlow<BaseUiState<List<Task>>?> = _observeTasksUiState.asStateFlow()
    private val _addTaskUiState = MutableStateFlow<BaseUiState<Unit>?>(null)
    val addTaskUiState: StateFlow<BaseUiState<Unit>?> = _addTaskUiState.asStateFlow()

    private val _updateTaskUiState = MutableStateFlow<BaseUiState<Unit>?>(null)
    val updateTaskUiState: StateFlow<BaseUiState<Unit>?> = _updateTaskUiState.asStateFlow()

    init {
        observeTasks()
    }

    fun observeTasks() {
        viewModelScope.launch {
            _observeTasksUiState.value = BaseUiState.Loading
            observeTasksUseCase().collect { tasks ->
                _observeTasksUiState.update {
                    BaseUiState.Success(tasks)
                }
            }
        }
    }

    fun addTask(
        task: Task
    ) {
        viewModelScope.launch {
            _addTaskUiState.value = BaseUiState.Loading

            when (val result = addTaskUseCase(task)) {
                is AppResult.Success -> {
                    _addTaskUiState.value = BaseUiState.Success(Unit)
                }

                is AppResult.Error -> {
                    _addTaskUiState.value = BaseUiState.Error(result.error.toMessage())
                }
            }
        }
    }

    fun updateTask(
        task: Task
    ) {
        viewModelScope.launch {
            _updateTaskUiState.value = BaseUiState.Loading
            when (val result = updateTaskUseCase(task)) {
                is AppResult.Success -> {
                    _updateTaskUiState.value = BaseUiState.Success(Unit)
                    observeTasks()
                }
                is AppResult.Error -> {
                    _updateTaskUiState.value = BaseUiState.Error(result.error.toMessage())
                }
            }
        }
    }
}