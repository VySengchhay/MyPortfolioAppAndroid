package com.androidapp.myportfolioappandroid.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.data.DashboardUiData
import com.androidapp.myportfolioappandroid.feature.dashboard.presentation.model.DashboardCardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUiData: DashboardUiData
) : ViewModel() {
    private val _dashboardCardUiModelList: MutableStateFlow<BaseUiState<List<DashboardCardUiModel>>> =
        MutableStateFlow(BaseUiState.Idle)
    val dashboardCardUiModelList = _dashboardCardUiModelList.asStateFlow()

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