package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.multiplephotopick

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlevideopick.SingleVideoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MultiplePhotoPickViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MultiplePhotoPickUiState())
    val uiState = _uiState.asStateFlow()

    fun onSelectedImage(imageUris: List<Uri>) {
        _uiState.update {
            it.copy(
                imageUris = imageUris
            )
        }
    }
}