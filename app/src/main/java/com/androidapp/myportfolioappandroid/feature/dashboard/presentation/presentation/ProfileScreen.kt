package com.androidapp.myportfolioappandroid.feature.dashboard.presentation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.androidapp.myportfolioappandroid.feature.auth.AuthEvent

@Composable
fun ProfileScreen(
    onBackClick:() -> Unit,
    onEvent: (AuthEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Screen",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = {
                onBackClick()
            }
        ) {
            Text(
                text = "Back"
            )
        }

        Button(
            onClick = {
                onEvent(AuthEvent.SignOut)
                println("=====> Test")
            }
        ) {
            Text(
                text = "Logout"
            )
        }
    }
}
