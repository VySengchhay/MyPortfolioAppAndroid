package com.androidapp.myportfolioappandroid.feature.apifeature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.data.FeatureApiUiData
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.model.FeatureApiUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val featureApiUiData: FeatureApiUiData
) : ViewModel() {
    private val _featureApiUiModelList = MutableStateFlow< BaseUiState<List<FeatureApiUiModel>>>(BaseUiState.Idle)
    val featureApiUiModelList: StateFlow<BaseUiState<List<FeatureApiUiModel>>> = _featureApiUiModelList.asStateFlow()

    init {
        getFeatureApiUiModelList(FeatureCategory.API)
    }

    fun getFeatureApiUiModelList(
        category: FeatureCategory
    ) {
        viewModelScope.launch {
            _featureApiUiModelList.emit(BaseUiState.Loading)
            featureApiUiData.getFeatureApiUiModelList(
                category = category
            ).collect {
                _featureApiUiModelList.emit(BaseUiState.Success(it))
            }
        }
    }
}