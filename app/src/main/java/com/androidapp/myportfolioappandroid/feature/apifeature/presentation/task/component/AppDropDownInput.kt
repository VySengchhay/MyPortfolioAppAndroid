package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownInput(
    selectedStatus: String,
    onStatusChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            onExpandedChange(it)
        }
    ) {

        OutlinedTextField(
            value = selectedStatus,
            onValueChange = { status ->
                onStatusChange(status)
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            label = {
                Text("Status")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onExpandedChange(false)
            }
        ) {

            DropdownMenuItem(
                text = {
                    Text("Not Completed")
                },
                onClick = {
                    onStatusChange("N")
                    onExpandedChange(false)
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Completed")
                },
                onClick = {
                    onStatusChange("Y")
                    onExpandedChange(false)
                }
            )
        }
    }
}