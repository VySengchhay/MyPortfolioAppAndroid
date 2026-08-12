package com.androidapp.myportfolioappandroid.feature.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.DashBoardScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.layoutlesson.ColumnLayoutScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.layoutlesson.RowLayoutScreen
import com.androidapp.myportfolioappandroid.navigation.ProfileRoute

fun NavGraphBuilder.dashBoardGraph(
    navController: NavHostController
) {
    navigation<DashboardGraph>(
        startDestination = DashboardRoute
    ) {
        composable<DashboardRoute> {
            DashBoardScreen(
                onProfileClick = {
                    navController.navigate(ProfileRoute)
                },
                onRowLayoutClick = { layoutId ->
                    navController.navigate(
                        RowLayoutRoute(
                            layoutId = layoutId
                        )
                    )
                },
                onColumnLayoutClick = { layoutId ->
                    navController.navigate(
                        ColumnLayoutRoute(
                            layoutId = layoutId
                        )
                    )
                }
            )
        }

        composable<RowLayoutRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<RowLayoutRoute>()

            RowLayoutScreen(
                layoutId = route.layoutId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<ColumnLayoutRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ColumnLayoutRoute>()

            ColumnLayoutScreen(
                layoutId = route.layoutId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}