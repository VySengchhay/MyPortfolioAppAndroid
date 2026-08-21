package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlephotopick

import android.net.Uri

sealed class SinglePhotoPickEvent {
    data class SelectedImage(
        val uri: Uri
    ) : SinglePhotoPickEvent()
}