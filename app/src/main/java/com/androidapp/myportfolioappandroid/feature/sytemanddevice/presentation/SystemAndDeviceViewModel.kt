package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.data.FeatureUiData
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.model.FeatureUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SystemAndDeviceViewModel @Inject constructor(
    private val featureUiData: FeatureUiData
) : ViewModel() {
    private val _systemAndDeviceUiModelList: MutableStateFlow<BaseUiState<List<FeatureUiModel>>> =
        MutableStateFlow<BaseUiState<List<FeatureUiModel>>>(BaseUiState.Idle)
    val systemAndDeviceUiModelList: StateFlow<BaseUiState<List<FeatureUiModel>>> = _systemAndDeviceUiModelList.asStateFlow()

    init {
        getFeatureUiModelList(FeatureCategory.SYSTEM_DEVICE)
    }

    fun getFeatureUiModelList(
        category: FeatureCategory
    ) {
        viewModelScope.launch {
            _systemAndDeviceUiModelList.emit(BaseUiState.Loading)
            featureUiData.getFeatureUiModelList(category).collect {
                _systemAndDeviceUiModelList.emit(BaseUiState.Success(it))
            }
        }
    }
}