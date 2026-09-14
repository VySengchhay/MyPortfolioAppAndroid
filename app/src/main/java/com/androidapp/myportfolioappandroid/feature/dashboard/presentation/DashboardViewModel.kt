package com.androidapp.myportfolioappandroid.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.common.extensions.nameFromEmail
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.auth.domain.usecase.ObserveCurrentUserUseCase
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.data.DashboardUiData
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.model.DashboardCardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUiData: DashboardUiData,
    observeCurrentUserUseCase: ObserveCurrentUserUseCase
) : ViewModel() {
    private val _dashboardCardUiModelList: MutableStateFlow<BaseUiState<List<DashboardCardUiModel>>> =
        MutableStateFlow(BaseUiState.Idle)
    val dashboardCardUiModelList = _dashboardCardUiModelList.asStateFlow()

    val userName: StateFlow<String> = observeCurrentUserUseCase()
        .map { user ->
            user?.displayName?.takeIf { it.isNotBlank() }
                ?: user?.email?.nameFromEmail()
                ?: "User"
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "User"
        )

    init {
        getDashboardCardUiModelList()
    }

    fun getDashboardCardUiModelList() {
        viewModelScope.launch {
            _dashboardCardUiModelList.emit(BaseUiState.Loading)
            dashboardUiData.getDashboardCardUiModelList().collect {
                _dashboardCardUiModelList.emit(BaseUiState.Success(it))
            }
        }

    }
}