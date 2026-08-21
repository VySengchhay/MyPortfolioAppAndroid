package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlephotopick

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SinglePhotoPickViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(SinglePhotoPickUiState(imageUri = savedStateHandle[IMAGE_URI_KEY]))
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SinglePhotoPickEvent) {
        when (event) {
            is SinglePhotoPickEvent.SelectedImage -> {
                onSelectedImage(event.uri)
            }
        }
    }

    private fun onSelectedImage(uri: Uri) {
        savedStateHandle[IMAGE_URI_KEY] = uri
        _uiState.value = SinglePhotoPickUiState(imageUri = uri)
    }

    private companion object {
        private const val IMAGE_URI_KEY = "image_uri"
    }
}