package com.androidapp.myportfolioappandroid.navigation

import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

@Serializable
data object SignUpRoute

@Serializable
data object ProfileRoute

@Serializable
data object DashboardRoute

@Serializable
data object NotificationRoute

@Serializable
data class LayoutRoute(
    val route : String
)

@Serializable
data class RowLayoutRoute(
    val route : String
)

@Serializable
data class ColumnLayoutRoute(
    val route : String
)


@Serializable
data class BoxLayoutRoute(
    val route : String
)

@Serializable
data class LazyRowLayoutRoute(
    val route : String
)

@Serializable
data class LazyColumnLayoutRoute(
    val route : String
)

@Serializable
data class ComponentRoute(
    val route : String
)

@Serializable
data class ApiRoute(
    val route : String
)

@Serializable
data class UserApiRoute(
    val route : String
)

@Serializable
data class TaskRoomDbRoute(
    val route : String
)

@Serializable
data object CreateTaskRoomDbRoute

@Serializable
data class UpdateTaskRoomDbRoute(
    val id: Int,
    val title: String,
    val description: String,
    val completeYN: String
)

@Serializable
data class ProductRoute(
    val route : String
)

@Serializable
data class ProductDetailRoute(
    val productId: Int
)



@Serializable
data class DeviceSystemRoute(
    val route : String
)

@Serializable
data class SinglePhotoPickRoute(
    val route : String
)

@Serializable
data class SingleVideoPickRoute(
    val route : String
)
@Serializable
data class MultiplePhotoPickRoute(
    val route : String
)

@Serializable
data class MultipleVideoPickRoute(
    val route : String
)

@Serializable
data class PhotoAndVideoPickRoute(
    val route : String
)

@Serializable
data class CameraLauncherRoute(
    val route : String
)

