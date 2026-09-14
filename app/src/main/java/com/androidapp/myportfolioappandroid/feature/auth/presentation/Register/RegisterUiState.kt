package com.androidapp.myportfolioappandroid.feature.auth.presentation.Register

data class RegisterUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val passwordsMismatch: Boolean
        get() = confirmPassword.isNotBlank() && password != confirmPassword

    val isFormValid: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
}
