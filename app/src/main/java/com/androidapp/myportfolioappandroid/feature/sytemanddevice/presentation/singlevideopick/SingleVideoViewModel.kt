package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlevideopick

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SingleVideoViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SingleVideoUiState())
    val uiState = _uiState.asStateFlow()

    fun onSelectedVideo(videoUri: Uri) {
        _uiState.update {
            it.copy(
                videoUri = videoUri
            )
        }
    }
}