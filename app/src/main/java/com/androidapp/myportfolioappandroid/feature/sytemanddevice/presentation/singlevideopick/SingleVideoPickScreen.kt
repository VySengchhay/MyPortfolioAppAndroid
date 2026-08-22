package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.singlevideopick

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.component.VideoPlayer

@Composable
fun SingleVideoPickScreen(
    modifier: Modifier = Modifier,
    viewModel: SingleVideoViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            viewModel.onSelectedVideo(uri)
        } else {
            Toast.makeText(
                context,
                "No video selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onSelectedVideo() {
        pickMedia.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.VideoOnly
            )
        )
    }

    FeatureScaffold(
        modifier = modifier,
        title = "Select Single Video",
        onBackClick = onBack,
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppSpacing.medium)
                ,
                onClick = {
                    onSelectedVideo()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Select Single Video"
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
            if (uiState.videoUri != null) {
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
                    uiState.videoUri?.let {
                        VideoPlayer(
                            videoUri = it
                        )
                    }
                }
            }
        }
    }
}