package com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.camerax

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraXScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onPreview: (imageUri: Uri) -> Unit = {}
) {
    val context = LocalContext.current

    val permissionState = rememberPermissionState(
        permission = android.Manifest.permission.CAMERA
    )

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    when {
        permissionState.status.isGranted -> {
            CameraScreen(
                actionClose = {
                    onBack()
                },
                onImageCaptured = { imageUri ->
                    onPreview(imageUri)
                }
            )
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        permissionState.launchPermissionRequest()
                    }
                ) {
                    Text("Allow Camera")
                }
            }
        }
    }
}
