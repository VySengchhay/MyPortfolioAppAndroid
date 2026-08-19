package com.androidapp.myportfolioappandroid.core.ui.state

sealed class BaseUiState<out T> {
    data object Idle : BaseUiState<Nothing>()
    data object Loading : BaseUiState<Nothing>()
    data class Success<T>(val data: T) : BaseUiState<T>()
    data class Error(val message: String) : BaseUiState<Nothing>()
    data class ErrorWithException(val exception: Exception) : BaseUiState<Nothing>()
}