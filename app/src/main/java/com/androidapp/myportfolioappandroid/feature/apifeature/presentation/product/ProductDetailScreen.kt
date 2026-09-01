package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product.component.ProductDetailCard

@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    productId: Int,
    viewModel: ProductViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val getProductByIdUiState by viewModel.getProductByIdUiState.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    FeatureScaffold(
        modifier = modifier,
        title = "Product Detail",
        onBackClick = onBack,
    ) { innerPadding ->
        when (val state = getProductByIdUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                println("=====> data: ${state.data}")

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    ProductDetailCard(product = state.data)
                }
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
}