package com.androidapp.myportfolioappandroid.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
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
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.DashBoardScreen
import com.androidapp.myportfolioappandroid.feature.profile.ProfileScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.LayoutFeatureScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.boxlayout.BoxLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.columnlayout.ColumnLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.horizontalpagerlayout.HorizontalPagerLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazycolumnlayout.LazyColumnLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazyhorizontalgridlayout.LazyHorizontalGridLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazyrowlayout.LazyRowLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazyverticalgridlayout.LazyVerticalGridLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.rowlayout.RowLayoutScreen
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.verticalpagerlayout.VerticalPagerLayoutScreen
import com.androidapp.myportfolioappandroid.feature.notification.NotificationScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.SystemAndDeviceScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.camerax.CameraXScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.camerax.ScreenPreviewImage
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.location.GetLocationScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.multiplephotopick.MultiplePhotoPickScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.multiplevideopick.MultipleVideoPickScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.photoandvideopick.PhotoAndVideoPickScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.singlephotopick.SinglePhotoPickScreen
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.singlevideopick.SingleVideoPickScreen

private fun NavBackStack<NavKey>.replaceAll(vararg keys: NavKey) {
    clear()
    addAll(keys)
}

@Composable
fun AppNavHost(
    backStack: NavBackStack<NavKey>,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val authUiState by authViewModel.authStateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<LoginRoute> {
                LaunchedEffect(authUiState) {
                    when (val state = authUiState) {

                        is AuthState.Authenticated -> {
                            backStack.replaceAll(DashboardRoute)
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
                        backStack.add(SignUpRoute)
                    },
                    onGoogleSignInClick = {},
                )
            }

            entry<SignUpRoute> {
                LaunchedEffect(authUiState) {
                    when (val state = authUiState) {

                        is AuthState.Authenticated -> {
                            backStack.replaceAll(DashboardRoute)
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
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<ProfileRoute> {
                LaunchedEffect(authUiState) {
                    when (authUiState) {
                        is AuthState.UnAuthenticated -> {
                            backStack.replaceAll(LoginRoute)
                        }

                        else -> Unit
                    }
                }
                val userName by authViewModel.userName.collectAsStateWithLifecycle()
                ProfileScreen(
                    userName = userName,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    },
                    onEvent = authViewModel::onEvent
                )
            }

            entry<DashboardRoute> {
                val userName by authViewModel.userName.collectAsStateWithLifecycle()

                DashBoardScreen(
                    modifier = Modifier,
                    userName = userName,
                    onProfileClick = {
                        backStack.add(ProfileRoute)
                    },
                    onNotificationClick = {
                        backStack.add(NotificationRoute)
                    },
                    onCategoryClick = { destination ->
                        backStack.add(destination)
                    }
                )
            }

            entry<NotificationRoute> {
                NotificationScreen(
                    modifier = Modifier,
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<LayoutRoute> {
                LayoutFeatureScreen(
                    modifier = Modifier,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    },
                    onFeatureClick = { destination ->
                        backStack.add(destination)
                    }
                )
            }

            entry<RowLayoutRoute> {
                RowLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<ColumnLayoutRoute> {
                ColumnLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<BoxLayoutRoute> {
                BoxLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<LazyRowLayoutRoute> {
                LazyRowLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<LazyColumnLayoutRoute> {
                LazyColumnLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<LazyVerticalGridLayoutRoute> {
                LazyVerticalGridLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<LazyHorizontalGridLayoutRoute> {
                LazyHorizontalGridLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<HorizontalPagerLayoutRoute> {
                HorizontalPagerLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<VerticalPagerLayoutRoute> {
                VerticalPagerLayoutScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<ApiRoute> {
                ApiScreen(
                    modifier = Modifier,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    },
                    onFeatureClick = { destination ->
                        backStack.add(destination)
                    }
                )
            }

            entry<DeviceSystemRoute> {
                SystemAndDeviceScreen(
                    modifier = Modifier,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    },
                    onFeatureClick = { destination ->
                        backStack.add(destination)
                    }
                )
            }

            entry<SinglePhotoPickRoute> {
                SinglePhotoPickScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<SingleVideoPickRoute> {
                SingleVideoPickScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<MultiplePhotoPickRoute> {
                MultiplePhotoPickScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<MultipleVideoPickRoute> {
                MultipleVideoPickScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<PhotoAndVideoPickRoute> {
                PhotoAndVideoPickScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<GetLocationRoute> {
                GetLocationScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<CameraXRoute> {
                CameraXScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    onPreview = { imageUri ->
                        backStack.add(
                            ImagePreviewRoute(imageUri = imageUri.toString())
                        )
                    }
                )
            }

            entry<ImagePreviewRoute> { route ->
                ScreenPreviewImage(
                    imageUri = route.imageUri.toUri(),
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<UserApiRoute> {
                UserApiScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<TaskRoomDbRoute> {
                TaskRoomDbScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    onCreateTask = {
                        backStack.add(CreateTaskRoomDbRoute)
                    },
                    onGoToUpdateTask = { task ->
                        backStack.add(
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

            entry<UpdateTaskRoomDbRoute> { route ->
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
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<CreateTaskRoomDbRoute> {
                CreateTaskRoomDbScreen(
                    task = null,
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<ProductRoute> {
                ProductScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    onProductDetail = {
                        backStack.add(
                            ProductDetailRoute(productId = it)
                        )
                    }
                )
            }

            entry<ProductDetailRoute> { route ->
                ProductDetailScreen(
                    productId = route.productId,
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}
