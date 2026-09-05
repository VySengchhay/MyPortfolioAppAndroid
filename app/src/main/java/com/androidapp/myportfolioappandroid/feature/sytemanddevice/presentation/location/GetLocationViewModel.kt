package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.location

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GetLocationViewModel @Inject constructor() : ViewModel() {
    private val _getLocationUiState: MutableStateFlow<GetLocationUiState> =
        MutableStateFlow(GetLocationUiState())
    val getLocationUiState: StateFlow<GetLocationUiState> = _getLocationUiState.asStateFlow()

    fun onLocationResult(latitude: Double, longitude: Double) {
        _getLocationUiState.value = _getLocationUiState.value.copy(
            latitude = latitude,
            longitude = longitude
        )
    }
}