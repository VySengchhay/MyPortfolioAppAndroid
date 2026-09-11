package com.androidapp.myportfolioappandroid.feature.dashboard.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.theme.GradientType
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.model.DashboardCardUiModel
import com.androidapp.myportfolioappandroid.navigation.ApiRoute
import com.androidapp.myportfolioappandroid.navigation.DeviceSystemRoute
import com.androidapp.myportfolioappandroid.navigation.LayoutRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardUiData @Inject constructor() {
    private val dashboardCardUiModelList: List<DashboardCardUiModel> = listOf(
        DashboardCardUiModel(
            id = 1,
            title = "Layout",
            description = "Build and organize UI elements using Jetpack Compose layout components.",
            category = "Layout",
            destination = LayoutRoute,
            imageRes = R.drawable.im_layout,
            gradientType = GradientType.PRIMARY
        ),
        DashboardCardUiModel(
            id = 2,
            title = "Api",
            description = "Connect your app to servers and external services to exchange data.",
            category = "Api",
            destination = ApiRoute,
            imageRes = R.drawable.im_api,
            gradientType = GradientType.TERTIARY
        ),
        DashboardCardUiModel(
            id = 3,
            title = "System & Device",
            description = "Access and use Android system services and device hardware features.",
            category = "SystemDevice",
            destination = DeviceSystemRoute,
            imageRes = R.drawable.im_device_system,
            gradientType = GradientType.ERROR
        ),
    )

    fun getDashboardCardUiModelList(): Flow<List<DashboardCardUiModel>> {
        return flow {
            emit(dashboardCardUiModelList)
        }
    }
}