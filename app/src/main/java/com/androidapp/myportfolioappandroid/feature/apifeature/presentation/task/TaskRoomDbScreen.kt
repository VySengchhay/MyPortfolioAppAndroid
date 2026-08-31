package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.common.extensions.showToast
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task.component.TaskCard

@Composable
fun TaskRoomDbScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskRoomDbViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onCreateTask: () -> Unit,
) {
    val context = LocalContext.current

    val observeTasksUiState by viewModel.observeTasksUiState.collectAsStateWithLifecycle()



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

                            }
                        )
                    }
                }
            }

            else -> {}
        }
    }
}