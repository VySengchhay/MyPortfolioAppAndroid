package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.boxfeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun BoxFeatureScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    FeatureScaffold(
        modifier = modifier,
        title = "Box",
        onBackClick = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ProfileHeaderCard(
                userName = "Vy SengChhay",
                userTitle = "Android Developer",
                modifier = Modifier.padding(AppSpacing.medium)
            )
        }
    }
}

@Composable
private fun ProfileHeaderCard(
    userName: String,
    userTitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(AppSpacing.medium))
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.im_bg
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                    )
                )
        )

        Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(AppSpacing.small),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(AppSpacing.extraMedium)
        ) {
            Text(
                text = "Available",
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = AppSpacing.small, vertical = AppSpacing.extraSmall)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(AppSpacing.medium)
        ) {
            Text(
                text = userName,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = userTitle,
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
