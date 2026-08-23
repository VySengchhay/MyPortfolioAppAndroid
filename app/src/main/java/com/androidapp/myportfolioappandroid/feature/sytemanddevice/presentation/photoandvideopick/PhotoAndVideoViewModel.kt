package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.photoandvideopick

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class PhotoAndVideoViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PhotoAndVideoPickUiState())
    val uiState = _uiState.asStateFlow()

    fun onSelectedImage(imageUri: Uri) {
        _uiState.update {
            it.copy(
                selectUri = imageUri,
                mediaType = MediaType.IMAGE
            )
        }
    }

    fun onSelectedVideo(videoUri: Uri) {
        _uiState.update {
            it.copy(
                selectUri = videoUri,
                mediaType = MediaType.VIDEO
            )
        }
    }
}