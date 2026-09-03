package com.androidapp.myportfolioappandroid.feature.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.feature.auth.AuthEvent

@Composable
fun ProfileScreen(
    userName: String,
    onBackClick: () -> Unit,
    onEvent: (AuthEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    FeatureScaffold(
        title = "Profile",
        onBackClick = onBackClick
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = AppSpacing.screenHorizontal,
                    vertical = AppSpacing.screenVertical
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(AppSpacing.extraLarge))

            Box(
                modifier = Modifier
                    .size(AppSpacing.extraExtraLarge * 2)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userName.take(1).uppercase(),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(AppSpacing.medium))

            Text(
                text = userName,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(AppSpacing.extraLarge))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(AppSpacing.large))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.small)
            ) {
                ProfileMenuItem(
                    icon = Icons.Default.Edit,
                    label = "Edit Profile",
                    onClick = { /* TODO */ }
                )
                ProfileMenuItem(
                    icon = Icons.Default.Settings,
                    label = "Settings",
                    onClick = { /* TODO */ }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = { onEvent(AuthEvent.SignOut) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppSpacing.componentHeight),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)
            ) {
                Text(text = "Logout")
            }

            Spacer(modifier = Modifier.height(AppSpacing.medium))
        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppSpacing.extraMedium))
            .clickable(onClick = onClick)
            .padding(
                horizontal = AppSpacing.medium,
                vertical = AppSpacing.extraMedium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(AppSpacing.extraMedium))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline
        )
    }
}
