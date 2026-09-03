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
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.ApiScreen
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product.ProductDetailScreen
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product.ProductScreen
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task.CreateTaskRoomDbScreen
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.task.TaskRoomDbScreen
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user.UserApiScreen
import com.androidapp.myportfolioappandroid.feature.auth.AuthState
import com.androidapp.myportfolioappandroid.feature.auth.AuthViewModel
import com.androidapp.myportfolioappandroid.feature.auth.login.LoginScreen
import com.androidapp.myportfolioappandroid.feature.auth.signup.SignUpScreen
import com.androidapp.myportfolioappandroid.feature.componentfeature.presentation.ComponentScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.DashBoardScreen
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.presentation.ProfileScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.LayoutFeatureScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.boxlayout.BoxLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.columnlayout.ColumnLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.rowlayout.RowLayoutScreen
import com.androidapp.myportfolioappandroid.feature.notification.NotificationScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.SystemAndDeviceScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.camera.CameraLauncherScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.multiplephotopick.MultiplePhotoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.multiplevideopick.MultipleVideoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.photoandvideopick.PhotoAndVideoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlephotopick.SinglePhotoPickScreen
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlevideopick.SingleVideoPickScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel(),
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
                onNotificationClick = {
                    navController.navigate(NotificationRoute)
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

        composable<NotificationRoute> {
            NotificationScreen(
                modifier = Modifier,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<LayoutRoute> {
            LayoutFeatureScreen(
                modifier = Modifier,
                onBackClick = {
                    navController.popBackStack()
                },
                onFeatureClick = { route ->
                    when (route) {
                        "row_route" -> navController.navigate(
                            RowLayoutRoute(route = route)
                        )

                        "column_route" -> navController.navigate(
                            ColumnLayoutRoute(route = route)
                        )

                        "box_route" -> navController.navigate(
                            BoxLayoutRoute(route = route)
                        )
                    }
                }
            )
        }

        composable<RowLayoutRoute> {
            RowLayoutScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<ColumnLayoutRoute> {
            ColumnLayoutScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<BoxLayoutRoute> {
            BoxLayoutScreen(
                onBack = {
                    navController.popBackStack()
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
                onFeatureClick = { route ->
                    when (route) {
                        "api_user_route" -> navController.navigate(
                            UserApiRoute(route = route)
                        )

                        "task_roomdb_route" -> navController.navigate(
                            TaskRoomDbRoute(route = route)
                        )

                        "product_route" -> navController.navigate(
                            ProductRoute(route = route)
                        )
                    }
                }
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

                        "camera_launcher_route" -> navController.navigate(
                            CameraLauncherRoute(route = route)
                        )

                        else -> Unit
                    }
                }
            )
        }

        composable<SinglePhotoPickRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<SinglePhotoPickRoute>()
            SinglePhotoPickScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<SingleVideoPickRoute> {
            SingleVideoPickScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<MultiplePhotoPickRoute> {
            MultiplePhotoPickScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<MultipleVideoPickRoute> {
            MultipleVideoPickScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<PhotoAndVideoPickRoute> {
            PhotoAndVideoPickScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<CameraLauncherRoute> {
            CameraLauncherScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<UserApiRoute> {
            UserApiScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<TaskRoomDbRoute> {
            TaskRoomDbScreen(
                onBack = {
                    navController.popBackStack()
                },
                onCreateTask = {
                    navController.navigate(CreateTaskRoomDbRoute)
                },
                onGoToUpdateTask = { task ->
                    navController.navigate(
                        UpdateTaskRoomDbRoute(
                            id = task.id,
                            title = task.title,
                            description = task.description,
                            completeYN = task.completeYN
                        )
                    )
                }
            )
        }

        composable<UpdateTaskRoomDbRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<UpdateTaskRoomDbRoute>()

            val task = route.id?.let {
                Task(
                    id = it,
                    title = route.title.orEmpty(),
                    description = route.description.orEmpty(),
                    completeYN = route.completeYN ?: "N"
                )
            }

            CreateTaskRoomDbScreen(
                task = task,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<CreateTaskRoomDbRoute> {
            CreateTaskRoomDbScreen(
                task = null,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<ProductRoute> {
            ProductScreen(
                onBack = {
                    navController.popBackStack()
                },
                onProductDetail = {
                    navController.navigate(
                        ProductDetailRoute(productId = it)
                    )
                }
            )
        }

        composable<ProductDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ProductDetailRoute>()
            ProductDetailScreen(
                productId = route.productId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

