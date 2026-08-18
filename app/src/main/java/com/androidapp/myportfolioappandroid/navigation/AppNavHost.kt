package com.androidapp.myportfolioappandroid.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidapp.myportfolioappandroid.feature.auth.AuthState
import com.androidapp.myportfolioappandroid.feature.auth.AuthViewModel
import com.androidapp.myportfolioappandroid.feature.auth.login.LoginScreen
import com.androidapp.myportfolioappandroid.feature.auth.signup.SignUpScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.navigation.DashboardRoute
import com.androidapp.myportfolioappandroid.feature.dashboard.navigation.dashBoardGraph
import com.androidapp.myportfolioappandroid.feature.profile.presentation.ProfileScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authUiState by authViewModel.authStateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(authUiState) {
        when (authUiState) {
            is AuthState.Authenticated -> navController.navigate(
                DashboardRoute
            )
            is AuthState.Error -> Toast.makeText(
                context,
                (authUiState as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(
                modifier = Modifier,
                authState = authUiState,
                onEvent = authViewModel::onEvent,
                onSignUpClick = {
                    navController.navigate(SignUpRoute)
                },
                onGoogleSignInClick = {},
            )
        }

        composable<SignUpRoute> {
            SignUpScreen(
                modifier = Modifier,
                authState = authUiState,
                onEvent = authViewModel::onEvent,
                onBackLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        dashBoardGraph(
            navController = navController
        )

        composable<ProfileRoute> {

            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                },

                onLogoutClick = {
                    navController.navigate(LoginRoute) {
                        popUpTo(LoginRoute) {
                            inclusive = true
                        }
                    }
                },
                authViewModel = authViewModel,
                navController = navController
            )
        }
    }

}
