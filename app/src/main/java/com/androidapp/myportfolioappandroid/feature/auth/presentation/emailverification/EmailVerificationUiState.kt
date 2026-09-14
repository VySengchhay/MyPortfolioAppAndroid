package com.androidapp.myportfolioappandroid.feature.auth.presentation.emailverification

data class EmailVerificationUiState(
    val email: String = "",
    val isVerified: Boolean = false,
    val isCancelling: Boolean = false,
    val isCancelled: Boolean = false,
    val cancelledDueToTimeout: Boolean = false,
    val deadlineMillis: Long? = null
)
