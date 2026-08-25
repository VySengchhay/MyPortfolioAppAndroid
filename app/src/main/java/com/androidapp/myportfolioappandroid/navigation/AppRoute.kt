package com.androidapp.myportfolioappandroid.navigation

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

