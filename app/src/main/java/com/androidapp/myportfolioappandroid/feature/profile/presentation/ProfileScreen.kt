package com.androidapp.myportfolioappandroid.feature.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidapp.myportfolioappandroid.feature.auth.AuthState
import com.androidapp.myportfolioappandroid.feature.auth.AuthViewModel
import com.androidapp.myportfolioappandroid.navigation.LoginRoute

@Composable
fun ProfileScreen(
    onBackClick:() -> Unit,
    onLogoutClick:() -> Unit,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val authState by authViewModel.authStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(authState) {
        when (val state = authState) {
            is AuthState.UnAuthenticated -> navController.navigate(LoginRoute)
            else -> Unit
        }
    }

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
                authViewModel.signOut()
            }
        ) {
            Text(
                text = "Logout"
            )
        }
    }
}
