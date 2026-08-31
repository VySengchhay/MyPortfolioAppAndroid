package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.util.query
import com.androidapp.myportfolioappandroid.core.common.extensions.showToast
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.isCompleted
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component.DropdownMenu
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task.component.TaskCard

@Composable
fun TaskRoomDbScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskRoomDbViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onCreateTask: () -> Unit,
    onGoToUpdateTask: (Task) -> Unit
) {
    val context = LocalContext.current

    val observeTasksUiState by viewModel.observeTasksUiState.collectAsStateWithLifecycle()
    val updateTaskUiState by viewModel.updateTaskUiState.collectAsStateWithLifecycle()

    var expendedIndex by rememberSaveable { mutableStateOf(-1) }

    fun onCompletedYN(task: Task) {
        viewModel.updateTask(
            task.copy(
                completeYN = if (task.isCompleted()) {
                    "N"
                } else {
                    "Y"
                }
            )
        )
    }

    LaunchedEffect(observeTasksUiState) {
        when (val state = observeTasksUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                println("=====> ${state.data}")
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                context.showToast(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                context.showToast(state.exception.message ?: "Unknown error")
            }

            else -> {}
        }
    }

    LaunchedEffect(updateTaskUiState) {
        when (val state = updateTaskUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                viewModel.observeTasks()
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                context.showToast(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                context.showToast(state.exception.message ?: "Unknown error")
            }

            else -> {}
        }
    }

    FeatureScaffold(
        modifier = modifier,
        title = "Task RoomDB",
        onBackClick = onBack,
        floatingActionButton = {
            Button(
                onClick = {
                    onCreateTask()
                }
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Task"
                    )
                    Text(
                        text = "Add Task",
                        modifier = Modifier.padding(start = AppSpacing.small)
                    )
                }
            }
        }
    ) { innerPadding ->
        when (val state = observeTasksUiState) {
            is BaseUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = AppSpacing.medium)
                ) {
                    items(
                        state.data.size,
                        key = { index ->
                            index
                        }
                    ) { index ->
                        val task = state.data[index]

                        TaskCard(
                            task = Task(
                                id = task.id,
                                title = task.title,
                                description = task.description,
                                completeYN = task.completeYN
                            ),
                            onClick = {
                                onCompletedYN(task)
                            },
                            trailingContent = {
                                Box(
                                    modifier = Modifier
                                ) {
                                    IconButton(
                                        onClick = {
                                            expendedIndex = if (expendedIndex == index) {
                                                -1
                                            } else {
                                                index
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = ""
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expendedIndex == index,
                                        onExpandedChange = { isExpanded ->
                                            expendedIndex = if (isExpanded) index else -1
                                        },
                                        onEditClick = {
                                            onGoToUpdateTask(task)
                                        },
                                        onRemoveClick = {

                                        }
                                    )
                                }
                            }
                        )
                    }
                }
            }

            else -> {}
        }
    }
}
