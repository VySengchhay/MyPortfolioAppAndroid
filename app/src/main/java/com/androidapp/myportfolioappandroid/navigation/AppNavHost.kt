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
import androidx.navigation.toRoute
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.ApiScreen
import com.androidapp.myportfolioappandroid.feature.auth.AuthState
import com.androidapp.myportfolioappandroid.feature.auth.AuthViewModel
import com.androidapp.myportfolioappandroid.feature.auth.login.LoginScreen
import com.androidapp.myportfolioappandroid.feature.auth.signup.SignUpScreen
import com.androidapp.myportfolioappandroid.feature.componentfeature.presentation.ComponentScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.DashBoardScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.LayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazycolumn.ColumnLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazyrow.RowLayoutScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.presentation.ProfileScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.SystemAndDeviceScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.multiplephotopick.MultiplePhotoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.multiplevideopick.MultipleVideoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.photoandvideopick.PhotoAndVideoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlephotopick.SinglePhotoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlevideopick.SingleVideoPickScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authUiState by authViewModel.authStateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LaunchedEffect(authUiState) {
                when (val state = authUiState) {

                    is AuthState.Authenticated -> {
                        navController.navigate(DashboardRoute) {
                            popUpTo(LoginRoute) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }

                    is AuthState.Error -> {
                        Toast.makeText(
                            context,
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> Unit
                }
            }

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
            LaunchedEffect(authUiState) {
                when (val state = authUiState) {

                    is AuthState.Authenticated -> {
                        navController.navigate(DashboardRoute) {
                            popUpTo(LoginRoute) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }

                    is AuthState.Error -> {
                        Toast.makeText(
                            context,
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> Unit
                }
            }

            SignUpScreen(
                modifier = Modifier,
                authState = authUiState,
                onEvent = authViewModel::onEvent,
                onBackLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<ProfileRoute> {
            LaunchedEffect(authUiState) {
                when (authUiState) {
                    is AuthState.UnAuthenticated -> {
                        navController.navigate(LoginRoute) {
                            popUpTo(DashboardRoute) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }

                    else -> Unit
                }
            }
            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onEvent = authViewModel::onEvent
            )
        }

        composable<DashboardRoute> {
            val userName by authViewModel.userName.collectAsStateWithLifecycle()

            DashBoardScreen(
                modifier = Modifier,
                userName = userName,
                onProfileClick = {
                    navController.navigate(ProfileRoute)
                },
                onCategoryClick = { route ->
                    when (route) {
                        "layout" -> navController.navigate(
                            LayoutRoute(route = route)
                        )

                        "component" -> navController.navigate(
                            ComponentRoute(route = route)
                        )

                        "api" -> navController.navigate(
                            ApiRoute(route = route)
                        )

                        "system_device" -> navController.navigate(
                            DeviceSystemRoute(route = route)
                        )

                        else -> Unit
                    }
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

        composable<LayoutRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<LayoutRoute>()
            LayoutScreen(
                modifier = Modifier,
                onBackClick = {
                    navController.popBackStack()
                },
                onFeatureClick = {

                }
            )
        }

        composable<ComponentRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ComponentRoute>()
            ComponentScreen(
                modifier = Modifier,
                onBackClick = {
                    navController.popBackStack()
                },
                onFeatureClick = {}
            )
        }

        composable<ApiRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ApiRoute>()
            ApiScreen(
                modifier = Modifier,
                onBackClick = {
                    navController.popBackStack()
                },
                onFeatureClick = {}
            )
        }

        composable<DeviceSystemRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<DeviceSystemRoute>()
            SystemAndDeviceScreen(
                modifier = Modifier,
                onBackClick = {
                    navController.popBackStack()
                },
                onFeatureClick = { route ->
                    when (route) {
                        "single_photo_pick_route" -> navController.navigate(
                            SinglePhotoPickRoute(route = route)
                        )

                        "single_video_pick_route" -> navController.navigate(
                            SingleVideoPickRoute(route = route)
                        )

                        "multiple_photo_pick_route" -> navController.navigate(
                            MultiplePhotoPickRoute(route = route)
                        )

                        "multiple_video_pick_route" -> navController.navigate(
                            MultipleVideoPickRoute(route = route)
                        )

                        "photo_and_video_pick_route" -> navController.navigate(
                            PhotoAndVideoPickRoute(route = route)
                        )

                        else -> Unit
                    }
                }
            )
        }

        composable<SinglePhotoPickRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<SinglePhotoPickRoute>()
            SinglePhotoPickScreen(
                modifier = Modifier,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<SingleVideoPickRoute> {
            SingleVideoPickScreen(
                modifier = Modifier,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<MultiplePhotoPickRoute> {
            MultiplePhotoPickScreen(
                modifier = Modifier,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<MultipleVideoPickRoute> {
            MultipleVideoPickScreen(
                modifier = Modifier,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<PhotoAndVideoPickRoute> {
            PhotoAndVideoPickScreen(
                modifier = Modifier,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
