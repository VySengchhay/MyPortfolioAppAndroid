package com.androidapp.myportfolioappandroid.feature.dashboard.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.theme.GradientType
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.model.DashboardCardUiModel
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
            route = "layout",
            imageRes = R.drawable.im_layout,
            gradientType = GradientType.PRIMARY
        ),
        DashboardCardUiModel(
            id = 2,
            title = "Component",
            description = "Reusable UI elements used to build consistent and interactive interfaces.",
            category = "Component",
            route = "component",
            imageRes = R.drawable.im_component,
            gradientType = GradientType.SECONDARY
        ),
        DashboardCardUiModel(
            id = 3,
            title = "Api",
            description = "Connect your app to servers and external services to exchange data.",
            category = "Api",
            route = "api",
            imageRes = R.drawable.im_api,
            gradientType = GradientType.TERTIARY
        ),
        DashboardCardUiModel(
            id = 4,
            title = "System & Device",
            description = "Access and use Android system services and device hardware features.",
            category = "SystemDevice",
            route = "system_device",
            imageRes = R.drawable.im_device_system,
            gradientType = GradientType.ERROR
        ),
    )

    suspend fun getDashboardCardUiModelList(): Flow<List<DashboardCardUiModel>> {
        delay(500)
        return flow {
            emit(dashboardCardUiModelList)
        }
    }
}