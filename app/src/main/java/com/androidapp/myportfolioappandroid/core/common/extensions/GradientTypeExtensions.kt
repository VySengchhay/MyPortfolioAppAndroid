package com.androidapp.myportfolioappandroid.core.common.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.androidapp.myportfolioappandroid.core.ui.theme.GradientType

@Composable
fun GradientType.colors(): List<Color> {
    return when (this) {
        GradientType.PRIMARY -> listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.primaryContainer
        )

        GradientType.SECONDARY -> listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.secondaryContainer
        )

        GradientType.TERTIARY -> listOf(
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.tertiaryContainer
        )

        GradientType.ERROR -> listOf(
            MaterialTheme.colorScheme.error,
            MaterialTheme.colorScheme.errorContainer
        )
    }
}