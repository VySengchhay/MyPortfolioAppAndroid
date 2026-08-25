package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component

import androidx.compose.foundation.background
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
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.common.extensions.firstLetter
import com.androidapp.myportfolioappandroid.core.common.extensions.nameFromEmail
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user.UserApiScreen

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    name : String,
    email: String,
    onMoreVertClick: () -> Unit
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
                text = name.firstLetter(),
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
                text = name,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = email,
                style = MaterialTheme.typography.bodySmall
            )
        }

        IconButton(
            onClick = {
                onMoreVertClick()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vert),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemCardPreview() {
    MyPortfolioAppAndroidTheme() {
        ItemCard(
            name = "Test",
            email = "test@gmail.com",
            onMoreVertClick = {}
        )
    }
}
