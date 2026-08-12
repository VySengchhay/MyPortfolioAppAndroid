package com.androidapp.myportfolioappandroid.feature.dashboard.navigation

import kotlinx.serialization.Serializable

@Serializable
data object DashboardGraph

@Serializable
data object DashboardRoute

@Serializable
data class RowLayoutRoute(
    val layoutId: String
)

@Serializable
data class ColumnLayoutRoute(
    val layoutId: String
)