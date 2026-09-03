package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureItemCard
import com.androidapp.myportfolioappandroid.core.ui.component.TopAppBarCategory
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component.ItemCard
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.SystemAndDeviceViewModel

@Composable
fun LayoutFeatureScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onFeatureClick: (String) -> Unit,
    layoutFeatureViewModel: LayoutFeatureViewModel = hiltViewModel()
) {
    val layoutFeatureUiState by layoutFeatureViewModel.layoutFeatureUiModelList.collectAsStateWithLifecycle()

    LaunchedEffect(layoutFeatureUiState) {
        when (val state = layoutFeatureUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Error,
            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCategory(
                title = "Layout",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when (val state = layoutFeatureUiState) {
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
                            state.data[index].id
                        }
                    ) {
                        FeatureItemCard(
                            title = state.data[it].title,
                            description = state.data[it].description,
                            imageRes = state.data[it].imageRes,
                            onClick = {
                                onFeatureClick(state.data[it].route)
                            }
                        )
                    }
                }
            }

            else -> {}
        }
    }
}