package com.androidapp.myportfolioappandroid.core.util

import androidx.compose.runtime.mutableStateOf

object LoadingUtil {
    var isLoading = mutableStateOf(false)
        private set

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}