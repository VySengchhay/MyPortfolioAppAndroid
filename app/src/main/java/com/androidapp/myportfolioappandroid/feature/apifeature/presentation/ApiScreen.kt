package com.androidapp.myportfolioappandroid.feature.apifeature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
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

@Composable
fun ApiScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onFeatureClick: (String) -> Unit,
    apiViewModel: ApiViewModel = hiltViewModel()
) {
    val apiUiState by apiViewModel.featureApiUiModelList.collectAsStateWithLifecycle()

    LaunchedEffect(apiUiState) {
        when (val state = apiUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success,
            is BaseUiState.Error,
            BaseUiState.Idle -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCategory(
                title = "Api",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when (val state = apiUiState) {
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

            else -> {}
        }
    }
}