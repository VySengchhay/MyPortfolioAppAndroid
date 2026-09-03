package com.androidapp.myportfolioappandroid.feature.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.common.extensions.colors
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.dashboard.component.AppHomeHeader
import com.androidapp.myportfolioappandroid.feature.dashboard.component.DashboardCard

@Composable
fun DashBoardScreen(
    modifier: Modifier,
    userName: String,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onCategoryClick: (String) -> Unit,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    val dashboardCardUiState by dashboardViewModel.dashboardCardUiModelList.collectAsStateWithLifecycle()

    LaunchedEffect(dashboardCardUiState) {
        when (val state = dashboardCardUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success,
            is BaseUiState.Error,
            BaseUiState.Idle -> {
                LoadingUtil.hideLoading()
            }

            else -> Unit
        }

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppHomeHeader(
                userName = userName.uppercase(),
                onProfileClick = onProfileClick,
                onNotificationClick = onNotificationClick,
            )
        }
    ) { innerPadding ->
        when (val state = dashboardCardUiState) {
            is BaseUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(
                            AppSpacing.medium
                        )
                        .fillMaxSize(),
                ) {
                    items(
                        count = state.data.size,
                        key = { index ->
                            state.data[index].id
                        }
                    ) {
                        DashboardCard(
                            modifier = Modifier
                                .padding(
                                    vertical = AppSpacing.small
                                )
                                .clickable(
                                    onClick = {
                                        onCategoryClick(state.data[it].route)
                                    }
                                )
                            ,
                            title = state.data[it].title,
                            description = state
                                .data[it]
                                .description,
                            colors = state.data[it].gradientType.colors(),
                            imageRes = state.data[it].imageRes!!
                        )
                    }
                }
            }

            else -> Unit
        }
    }
}
