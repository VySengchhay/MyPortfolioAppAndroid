package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.common.extensions.firstLetter
import com.androidapp.myportfolioappandroid.core.common.extensions.nameFromEmail
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user.UserApiScreen
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user.component.DropdownMenu

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    item: User,
    onClick: (User) -> Unit,
    trailingIcon: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppSpacing.medium,
                vertical = AppSpacing.small
            )
            .clickable(
                onClick = {
                    onClick(item)
                }
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

