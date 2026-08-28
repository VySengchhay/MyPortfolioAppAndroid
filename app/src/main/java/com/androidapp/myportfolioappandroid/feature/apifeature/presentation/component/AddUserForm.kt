package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AddUserForm(
    name: String,
    email: String,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    isShowDialog: Boolean = false,
) {
    if (isShowDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text("Add User")
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = name,
                        onValueChange = onNameChanged,
                        label = { Text("Name") }
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = onEmailChanged,
                        label = { Text("Email") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = { /* Save */ }) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { /* Cancel */ }) {
                    Text("Cancel")
                }
            }
        )
    }
}