package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.androidapp.myportfolioappandroid.core.common.extensions.firstLetter
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.User

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    item: User,
    onClick: (User) -> Unit,
    trailingIcon: @Composable () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(item)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppSpacing.extraSmall
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppSpacing.medium,
                    vertical = AppSpacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(AppSpacing.extraExtraLarge)
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.name.firstLetter(),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(
                modifier = Modifier
                    .width(AppSpacing.medium)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            bottom = AppSpacing.extraSmall
                        ),
                    text = item.name,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = item.email,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )


            trailingIcon()
        }
    }
}

