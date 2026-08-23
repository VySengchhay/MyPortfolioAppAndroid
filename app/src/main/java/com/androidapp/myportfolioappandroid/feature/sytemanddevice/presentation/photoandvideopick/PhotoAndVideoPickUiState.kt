package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.photoandvideopick

import android.net.Uri

data class PhotoAndVideoPickUiState(
    val selectUri: Uri? = null,
    val mediaType: MediaType? = null
)
