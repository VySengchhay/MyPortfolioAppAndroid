package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureItemCard
import com.androidapp.myportfolioappandroid.core.ui.component.TopAppBarCategory
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil

@Composable
fun SystemAndDeviceScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onFeatureClick: (String) -> Unit,
    systemAndDeviceViewModel: SystemAndDeviceViewModel = hiltViewModel()
) {
    val systemAndDeviceUiState by systemAndDeviceViewModel.systemAndDeviceUiModelList.collectAsStateWithLifecycle()

    LaunchedEffect(systemAndDeviceUiState) {
        when (val state = systemAndDeviceUiState) {
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
            TopAppBarCategory(
                title = "Device & System",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when (val state = systemAndDeviceUiState) {
            is BaseUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = AppSpacing.screenHorizontal)
                        .padding(vertical = AppSpacing.screenVertical)
                ) {
                    items(
                        count = state.data.size,
                        key = { index ->
                            index
                        }
                    ) {
                        val data = state.data[it]

                        FeatureItemCard(
                            title = data.title,
                            description = data.description,
                            imageRes = data.imageRes,
                            onClick = {
                                onFeatureClick(data.route)
                            },
                        )
                    }
                }
            }

            else -> Unit
        }
    }
}