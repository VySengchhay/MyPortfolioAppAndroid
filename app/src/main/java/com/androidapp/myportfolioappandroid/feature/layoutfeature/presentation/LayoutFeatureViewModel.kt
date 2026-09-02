package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.data.FeatureLayoutUiData
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.model.FeatureLayoutUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LayoutFeatureViewModel @Inject constructor(
    private val featureLayoutUiData: FeatureLayoutUiData
) : ViewModel() {
    private val _layoutFeatureUiModelList: MutableStateFlow<BaseUiState<List<FeatureLayoutUiModel>>> =
        MutableStateFlow<BaseUiState<List<FeatureLayoutUiModel>>>(BaseUiState.Idle)
    val layoutFeatureUiModelList: StateFlow<BaseUiState<List<FeatureLayoutUiModel>>> = _layoutFeatureUiModelList.asStateFlow()

    init {
        getFeatureLayoutUiModelList(FeatureCategory.LAYOUT)
    }

    fun getFeatureLayoutUiModelList(
        category: FeatureCategory
    ) {
        viewModelScope.launch {
            _layoutFeatureUiModelList.emit(BaseUiState.Loading)
            featureLayoutUiData.getFeatureLayoutUiModelList(category).collect {
                _layoutFeatureUiModelList.emit(BaseUiState.Success(it))
            }
        }
    }
}