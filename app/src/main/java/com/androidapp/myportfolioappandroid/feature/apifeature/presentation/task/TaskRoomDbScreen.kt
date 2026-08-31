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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task.component.TaskCard

@Composable
fun TaskRoomDbScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onCreateTask: () -> Unit,
) {
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = AppSpacing.medium)
        ) {
            items(10) {
                TaskCard(
                    task = Task(
                        id = 1,
                        title = "Task Title",
                        description = "Task Description",
                        completeYN = "N"
                    ),
                    onClick = {

                    }
                )
            }
        }
    }
}