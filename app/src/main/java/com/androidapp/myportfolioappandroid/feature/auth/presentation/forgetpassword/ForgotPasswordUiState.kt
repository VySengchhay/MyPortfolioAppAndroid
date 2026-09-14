package com.androidapp.myportfolioappandroid.feature.auth.presentation.forgetpassword

data class ForgotPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEmailSent: Boolean = false,
    val resendCooldownSeconds: Int = 0
) {
    val isFormValid: Boolean
        get() = email.isNotBlank()

    val canResend: Boolean
        get() = isEmailSent && !isLoading && resendCooldownSeconds <= 0
}