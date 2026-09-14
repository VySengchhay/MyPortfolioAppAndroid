package com.androidapp.myportfolioappandroid.feature.auth.presentation.forgetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.core.common.toMessage
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val RESEND_COOLDOWN_SECONDS = 30

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState.asStateFlow()

    private var cooldownJob: Job? = null

    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value, errorMessage = null)
    }

    fun sendResetLink() {
        val state = _uiState.value
        if (state.isLoading) return
        if (!state.isFormValid) return
        if (state.isEmailSent && state.resendCooldownSeconds > 0) return

        viewModelScope.launch {
            _uiState.value = state.copy(isLoading = true, errorMessage = null)

            when (
                val result = forgotPasswordUseCase(email = state.email)) {
                is AppResult.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, isEmailSent = true)
                    startResendCooldown()
                }
                is AppResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.error.toMessage()
                    )
                }

                AppResult.Loading -> Unit
            }
        }
    }

    private fun startResendCooldown() {
        cooldownJob?.cancel()
        cooldownJob = viewModelScope.launch {
            for (remaining in RESEND_COOLDOWN_SECONDS downTo 1) {
                _uiState.value = _uiState.value.copy(resendCooldownSeconds = remaining)
                delay(1_000)
            }
            _uiState.value = _uiState.value.copy(resendCooldownSeconds = 0)
        }
    }

    fun consumeError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}