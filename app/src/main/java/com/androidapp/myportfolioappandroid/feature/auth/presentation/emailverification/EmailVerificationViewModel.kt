package com.androidapp.myportfolioappandroid.feature.auth.presentation.emailverification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.CancelUnverifiedRegistrationUseCase
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.CheckEmailVerificationUseCase
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.LogoutUseCase
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.ObserveCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val VERIFICATION_WINDOW_MS = 60_000L

@HiltViewModel
class EmailVerificationViewModel @Inject constructor(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val checkEmailVerificationUseCase: CheckEmailVerificationUseCase,
    private val cancelUnverifiedRegistrationUseCase: CancelUnverifiedRegistrationUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmailVerificationUiState())
    val uiState: StateFlow<EmailVerificationUiState> = _uiState.asStateFlow()

    private var currentUid: String? = null

    init {
        viewModelScope.launch {
            observeCurrentUserUseCase().collect { user ->
                _uiState.update {
                    // This ViewModel outlives a single dialog, so start fresh for each new
                    // registration instead of carrying over the previous attempt's cancelled state.
                    val base = if (user != null && user.uid != currentUid) {
                        currentUid = user.uid
                        EmailVerificationUiState()
                    } else {
                        it
                    }
                    base.copy(
                        email = user?.email.orEmpty(),
                        deadlineMillis = user?.createdAtMillis?.let { createdAt ->
                            createdAt + VERIFICATION_WINDOW_MS
                        }
                    )
                }
            }
        }
    }

    fun checkVerification() {
        viewModelScope.launch {
            when (val result = checkEmailVerificationUseCase()) {
                is AppResult.Success -> {
                    if (result.data.isEmailVerified) {
                        onEmailVerified()
                    }
                }

                is AppResult.Error, AppResult.Loading -> Unit
            }
        }
    }

    fun cancel(dueToTimeout: Boolean = false) {
        if (_uiState.value.isCancelling || _uiState.value.isCancelled || _uiState.value.isVerified) return

        _uiState.update { it.copy(isCancelling = true) }

        viewModelScope.launch {
            // The link may have been tapped after the last poll; never delete a verified account.
            val verification = checkEmailVerificationUseCase()
            if (verification is AppResult.Success && verification.data.isEmailVerified) {
                onEmailVerified()
                return@launch
            }

            cancelUnverifiedRegistrationUseCase()
            _uiState.update {
                it.copy(isCancelling = false, isCancelled = true, cancelledDueToTimeout = dueToTimeout)
            }
        }
    }

    private suspend fun onEmailVerified() {
        // Polling and resume checks can both see the verified user; handle it only once.
        if (_uiState.value.isVerified) return
        _uiState.update { it.copy(isCancelling = false, isVerified = true) }

        // Registration ends signed out so the user logs in with their new account. Sign-out
        // happens synchronously before any suspension, so the refreshed verified session never
        // reaches the UI and never redirects to the dashboard.
        logoutUseCase()
    }

    fun onCancellationHandled() {
        _uiState.update { it.copy(isCancelled = false, cancelledDueToTimeout = false) }
    }

    fun onVerificationHandled() {
        _uiState.update { it.copy(isVerified = false) }
    }
}
