package com.androidapp.myportfolioappandroid.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.LogoutUseCase
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.ObserveCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val uiState: StateFlow<ProfileUiState> = observeCurrentUserUseCase()
        .map { user ->
            ProfileUiState(
                fullName = user?.displayName,
                email = user?.email,
                isLoggedOut = user == null
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProfileUiState()
        )

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}