package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.multiplephotopick

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun MultiplePhotoPickScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: MultiplePhotoPickViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(5)
    ) { uris ->
        if (uris.isNotEmpty()) {
            viewModel.onSelectedImage(uris)
        } else {
            Toast.makeText(
                context,
                "No image selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onSelectedImage() {
        pickMedia.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    FeatureScaffold(
        modifier = modifier,
        title = "Select Multiple Images",
        onBackClick = onBack,
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppSpacing.medium)
                ,
                onClick = {
                    onSelectedImage()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Select Multiple Image"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            uiState.imageUris?.let {
                it.forEach { imageUri ->
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
                            model = imageUri,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
