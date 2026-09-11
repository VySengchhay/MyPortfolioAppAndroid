package com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.singlephotopick

import android.net.Uri

sealed class SinglePhotoPickEvent {
    data class SelectedImage(
        val uri: Uri
    ) : SinglePhotoPickEvent()
}