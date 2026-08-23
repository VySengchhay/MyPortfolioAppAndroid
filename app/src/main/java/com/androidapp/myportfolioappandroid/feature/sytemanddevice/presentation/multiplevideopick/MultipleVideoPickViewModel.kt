package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.multiplevideopick

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MultipleVideoPickViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MultipleVideoPickUiState())
    val uiState = _uiState.asStateFlow()

    fun onSelectedImage(videoUris: List<Uri>) {
        _uiState.update {
            it.copy(
                videoUris = videoUris
            )
        }
    }
}