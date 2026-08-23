package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CameraLauncherViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CameraLauncherUiState())
    val uiState = _uiState.asStateFlow()

    fun onImageCaptured(bitmap: Bitmap) {
        _uiState.update {
            it.copy(
                resultBitmap = bitmap
            )
        }
    }
}