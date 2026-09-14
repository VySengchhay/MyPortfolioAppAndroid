package com.androidapp.myportfolioappandroid.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.ObserveCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface StartupSession {
    data object Unknown : StartupSession
    data object SignedOut : StartupSession
    data object PendingVerification : StartupSession
    data object Verified : StartupSession
}

@HiltViewModel
class AppNavHostViewModel @Inject constructor(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase
) : ViewModel() {
    val startupSession: StateFlow<StartupSession> = observeCurrentUserUseCase()
        .map { user ->
            when {
                user == null -> StartupSession.SignedOut
                !user.isEmailVerified -> StartupSession.PendingVerification
                else -> StartupSession.Verified
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StartupSession.Unknown
        )
}
