package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.columnlayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun ColumnLayoutScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    FeatureScaffold(
        modifier = modifier,
        title = "Column",
        onBackClick = onBack
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(
                count = 10,
                key = { it }
            ) { index ->
                ProfileDetailsSection(
                    name = "Vy Sengchhay",
                    role = "Android Developer",
                    bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    skills = listOf("Kotlin", "Java", "Android", "Jetpack Compose")
                )

                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ProfileDetailsSection(
    name: String,
    role: String,
    bio: String,
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppSpacing.screenHorizontal,
                vertical = AppSpacing.screenVertical
            ),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.medium)
    ) {
        // Name + role block
        Column(
            verticalArrangement = Arrangement.spacedBy(AppSpacing.extraExtraSmall)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = role,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = bio,
            style = MaterialTheme.typography.bodyMedium
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(AppSpacing.extraSmall)
        ) {
            Text(
                text = "Skills",
                style = MaterialTheme.typography.labelLarge
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(AppSpacing.extraSmall),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.extraSmall)
            ) {
                skills.forEach { skill ->
                    AssistChip(
                        onClick = {},
                        label = { Text(skill) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(AppSpacing.large))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(AppSpacing.componentHeight)
        ) {
            Text("Contact Me")
        }
    }
}