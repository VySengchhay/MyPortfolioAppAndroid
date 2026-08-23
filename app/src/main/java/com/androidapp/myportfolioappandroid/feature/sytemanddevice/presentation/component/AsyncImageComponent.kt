package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.component

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun AsyncImageComponent(
    imageUri: Uri
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