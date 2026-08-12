package com.androidapp.myportfolioappandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidapp.myportfolioappandroid.feature.auth.presentation.LoginScreen
import com.androidapp.myportfolioappandroid.feature.auth.presentation.RegisterScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.navigation.DashboardGraph
import com.androidapp.myportfolioappandroid.feature.dashboard.navigation.DashboardRoute
import com.androidapp.myportfolioappandroid.feature.dashboard.navigation.dashBoardGraph
import com.androidapp.myportfolioappandroid.feature.profile.presentation.ProfileScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(DashboardGraph)
                },

                onRegisterClick = {
                    navController.navigate(RegisterRoute)
                }
            )
        }

        composable<RegisterRoute> {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(LoginRoute)
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
                }
            )
        }
    }

}