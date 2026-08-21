package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlephotopick

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.androidapp.myportfolioappandroid.core.ui.component.TopAppBarCategory
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun SinglePhotoPickScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: SinglePhotoPickViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val selectSingleImageViewModel by viewModel.uiState.collectAsStateWithLifecycle()
    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            viewModel.onEvent(
                SinglePhotoPickEvent.SelectedImage(uri)
            )
        } else {
            Toast.makeText(
                context,
                "No image selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onPickImage() {
        val visualImage = PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        pickMedia.launch(visualImage)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCategory(
                title = "Select Image",
                onBackClick = onBack
            )
        },
        floatingActionButton = {
            Button(
                onClick = {
                    onPickImage()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = "Select Image"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectSingleImageViewModel.imageUri != null) {
                Box(
                    modifier = Modifier
                        .padding(AppSpacing.medium)
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .clip(
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        )
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(AppSpacing.extraSmall)
                            .wrapContentHeight()
                            .clip(
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentScale = ContentScale.Fit,
                        model = selectSingleImageViewModel.imageUri,
                        contentDescription = null
                    )
                }
            }
        }
    }
}