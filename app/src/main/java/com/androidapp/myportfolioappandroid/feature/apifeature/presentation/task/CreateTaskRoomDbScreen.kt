package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.ValidationUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.isCompleted
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task.component.AppDropDownInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskRoomDbScreen(
    modifier: Modifier = Modifier,
    task: Task? = null,
    viewModel: TaskRoomDbViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    var title by rememberSaveable { mutableStateOf(task?.title ?: "") }
    var titleError by rememberSaveable { mutableStateOf<String?>(null) }

    var description by rememberSaveable { mutableStateOf(task?.description ?: "") }
    var descriptionError by rememberSaveable { mutableStateOf<String?>(null) }

    var expanded by rememberSaveable { mutableStateOf(false) }
//
//    val selectedStatus = if (task?.completeYN == "Y") {
//        "Completed"
//    } else {
//        "Not Completed"
//    }

    var isCompleted by rememberSaveable { mutableStateOf(task?.isCompleted() ?: false) }

    fun onCreateTask() {
        titleError = ValidationUtil.validateTitle(title)
        descriptionError = ValidationUtil.validateDescription(description)

        if (titleError != null || descriptionError != null) return

        val task = Task(
            title = title,
            description = description,
            completeYN = if (isCompleted) {
                "Y"
            } else {
                "N"
            }
        )

        viewModel.addTask(task)

        println("=====> $task")

        onBack()

    }

    FeatureScaffold(
        modifier = modifier
            .navigationBarsPadding(),
        title = "Create Task RoomDB",
        onBackClick = onBack,
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = AppSpacing.medium
                    )
                ,
                onClick = {
                    onCreateTask()
                }
            ) {
                Text(
                    text = "Create Task"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = AppSpacing.medium)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { value ->
                    title = value
                    if (titleError != null) {
                        titleError = ValidationUtil.validateTitle(value)
                    }
                },
                label = {
                    Text("Title")
                },
                modifier = Modifier.fillMaxWidth(),
                isError = titleError != null,
                supportingText = {
                    titleError?.let { errorMessage ->
                        Text(
                            text = errorMessage
                        )
                    }
                }
            )

            OutlinedTextField(
                value = description,
                onValueChange = { value ->
                    description = value
                    if (descriptionError != null) {
                        descriptionError = ValidationUtil.validateDescription(value)
                    }
                },
                label = {
                    Text("Description")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.3f)
                ,
                isError = descriptionError != null,
                supportingText = {
                    descriptionError?.let { errorMessage ->
                        Text(
                            text = errorMessage
                        )
                    }
                }
            )

            AppDropDownInput(
                selectedStatus = if (isCompleted) {
                    "Completed"
                } else {
                    "Not Completed"
                },
                onStatusChange = { status ->
                    isCompleted = status == "Y"
                },
                expanded = expanded,
                onExpandedChange = { isExpanded ->
                    expanded = isExpanded
                }
            )
        }
    }
}