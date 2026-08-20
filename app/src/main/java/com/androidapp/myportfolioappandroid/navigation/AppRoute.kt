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
data class DeviceSystemRoute(
    val route : String
)


@Serializable
data class RowLayoutRoute(
    val layoutId: String
)

@Serializable
data class ColumnLayoutRoute(
    val layoutId: String
)
