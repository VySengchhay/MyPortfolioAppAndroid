package com.androidapp.myportfolioappandroid.navigation

import androidx.navigation3.runtime.NavKey
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.task.Task
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute : NavKey

@Serializable
data object SignUpRoute : NavKey

@Serializable
data object ProfileRoute : NavKey

@Serializable
data object DashboardRoute : NavKey

@Serializable
data object NotificationRoute : NavKey

@Serializable
data object LayoutRoute : NavKey

@Serializable
data object RowLayoutRoute : NavKey

@Serializable
data object ColumnLayoutRoute : NavKey

@Serializable
data object BoxLayoutRoute : NavKey

@Serializable
data object LazyRowLayoutRoute : NavKey

@Serializable
data object LazyColumnLayoutRoute : NavKey

@Serializable
data object LazyVerticalGridLayoutRoute : NavKey

@Serializable
data object LazyHorizontalGridLayoutRoute : NavKey

@Serializable
data object HorizontalPagerLayoutRoute : NavKey

@Serializable
data object VerticalPagerLayoutRoute : NavKey

@Serializable
data object ApiRoute : NavKey

@Serializable
data object UserApiRoute : NavKey

@Serializable
data object TaskRoomDbRoute : NavKey

@Serializable
data object CreateTaskRoomDbRoute : NavKey

@Serializable
data class UpdateTaskRoomDbRoute(
    val id: Int,
    val title: String,
    val description: String,
    val completeYN: String
) : NavKey

@Serializable
data object ProductRoute : NavKey

@Serializable
data class ProductDetailRoute(
    val productId: Int
) : NavKey

@Serializable
data object DeviceSystemRoute : NavKey

@Serializable
data object SinglePhotoPickRoute : NavKey

@Serializable
data object SingleVideoPickRoute : NavKey

@Serializable
data object MultiplePhotoPickRoute : NavKey

@Serializable
data object MultipleVideoPickRoute : NavKey

@Serializable
data object PhotoAndVideoPickRoute : NavKey

@Serializable
data object GetLocationRoute : NavKey

@Serializable
data object CameraXRoute : NavKey

@Serializable
data class ImagePreviewRoute(
    val imageUri: String
) : NavKey
