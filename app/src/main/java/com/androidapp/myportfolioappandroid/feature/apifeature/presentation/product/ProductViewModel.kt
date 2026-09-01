package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.core.common.toMessage
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.product.GetAllProductUseCase
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.product.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {
    private val _getAllProductUiState = MutableStateFlow<BaseUiState<List<Product>>?>(null)
    val getAllProductUiState = _getAllProductUiState.asStateFlow()

    private val _getProductByIdUiState = MutableStateFlow<BaseUiState<Product>?>(null)
    val getProductByIdUiState = _getProductByIdUiState.asStateFlow()

    init {
        getAllProduct()
    }

    fun getAllProduct() {
        viewModelScope.launch {
            _getAllProductUiState.value = BaseUiState.Loading
            when (val result = getAllProductUseCase()) {
                is AppResult.Success -> {
                    _getAllProductUiState.value = BaseUiState.Success(result.data)
                }

                is AppResult.Error -> {
                    _getAllProductUiState.value = BaseUiState.Error(result.error.toMessage())
                }
            }
        }
    }

    fun getProductById(
        id: Int
    ) {
        viewModelScope.launch {
            _getProductByIdUiState.value = BaseUiState.Loading
            when (val result = getProductByIdUseCase(id)) {
                is AppResult.Success -> {
                    _getProductByIdUiState.value = BaseUiState.Success(result.data)
                }
                is AppResult.Error -> {
                    _getProductByIdUiState.value = BaseUiState.Error(result.error.toMessage())
                }
            }
        }
    }
}