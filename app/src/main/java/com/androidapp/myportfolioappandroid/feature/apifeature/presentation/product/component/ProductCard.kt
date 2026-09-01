package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(AppSpacing.medium),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppSpacing.extraSmall
        )
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(AppSpacing.extraMedium),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier.padding(
                    start = AppSpacing.extraMedium,
                    end = AppSpacing.extraMedium,
                    bottom = AppSpacing.extraMedium
                )
            ) {

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(AppSpacing.extraSmallMedium)
                )

                Text(
                    text = "$${product.price}",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(AppSpacing.extraSmall)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(AppSpacing.medium),
                        tint = MaterialTheme.colorScheme.onError
                    )

                    Spacer(
                        modifier = Modifier.width(AppSpacing.extraSmall)
                    )

                    Text(
                        text = "${product.rating}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}