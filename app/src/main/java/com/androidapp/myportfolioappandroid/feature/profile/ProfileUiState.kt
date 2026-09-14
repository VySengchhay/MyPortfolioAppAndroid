package com.androidapp.myportfolioappandroid.feature.profile

data class ProfileUiState(
    val fullName: String? = null,
    val email: String? = null,
    val isLoggedOut: Boolean = false
)