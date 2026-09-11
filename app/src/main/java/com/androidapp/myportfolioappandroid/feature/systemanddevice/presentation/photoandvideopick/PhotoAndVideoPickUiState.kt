package com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.photoandvideopick

import android.net.Uri

data class PhotoAndVideoPickUiState(
    val selectUri: Uri? = null,
    val mediaType: MediaType? = null
)
