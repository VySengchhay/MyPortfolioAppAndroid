package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.common.extensions.showToast
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product.component.ProductCard

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val getAllProductUiState by viewModel.getAllProductUiState.collectAsStateWithLifecycle()

    LaunchedEffect(getAllProductUiState) {
        when (val state = getAllProductUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                println("=====> data: ${state.data}")
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                context.showToast(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                context.showToast(state.exception.message ?: "Unknown error")
            }

            else -> {}
        }
    }

    FeatureScaffold(
        modifier = modifier,
        title = "Product",
        onBackClick = onBack,
    ) { innerPadding ->
        when (val state = getAllProductUiState) {
            is BaseUiState.Success -> {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(AppSpacing.medium),
                    horizontalArrangement = Arrangement.spacedBy(AppSpacing.extraMedium),
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.extraMedium)
                ) {
                    items(
                        state.data.size
                    ) { index ->
                        val product = state.data[index]
                        ProductCard(
                            product = product,
                            onClick = {
                            }
                        )
                    }
                }
            }

            else -> {}
        }
    }
}