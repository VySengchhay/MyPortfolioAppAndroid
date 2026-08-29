package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
