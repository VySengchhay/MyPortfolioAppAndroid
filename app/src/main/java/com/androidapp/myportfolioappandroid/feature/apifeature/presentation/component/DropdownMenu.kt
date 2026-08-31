package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DropdownMenu(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            onExpandedChange(false)
         }
    ) {
        DropdownMenuItem(
            text = {
                Text("Edit")
            },
            onClick = {
                onExpandedChange(false)
                onEditClick()
            }
        )
        DropdownMenuItem(
            text = {
                Text("Remove")
            },
            onClick = {
                onExpandedChange(false)
                onRemoveClick()
            }
        )
    }
}
