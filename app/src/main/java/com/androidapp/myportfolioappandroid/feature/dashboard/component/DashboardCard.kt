package com.androidapp.myportfolioappandroid.feature.dashboard.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme

@Composable
fun DashboardCard(
    modifier: Modifier,
    title: String,
    description: String,
    colors: List<Color>,
    @DrawableRes imageRes: Int,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(AppSpacing.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = colors
                    )
                )
                .padding(
                start = 22.dp,
                end = 20.dp,
                top = 18.dp,
                bottom = 14.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize(.6f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(AppSpacing.medium))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painterResource(id = imageRes),
                contentDescription = "Google",
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DashboardCardPreview() {
    MyPortfolioAppAndroidTheme() {
        DashboardCard(
            modifier = Modifier,
            title = "Layout",
            description = "Build and organize UI elements using Jetpack Compose layout components.",
            colors = listOf<Color>(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.secondary
            ),
            imageRes = R.drawable.ic_google
        )
    }
}
