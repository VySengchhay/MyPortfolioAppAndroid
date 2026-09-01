package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.product.component

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.ui.theme.Warning
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product

@Composable
fun ProductDetailCard(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppSpacing.medium),
        shape = RoundedCornerShape(AppSpacing.largeMedium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .padding(AppSpacing.large),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = product.category.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.height(AppSpacing.small)
                )

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(AppSpacing.extraMedium)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Warning,
                        modifier = Modifier.size(AppSpacing.largeMedium)
                    )

                    Spacer(
                        modifier = Modifier.width(AppSpacing.extraSmall)
                    )

                    Text(
                        text = product.rating.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(
                        modifier = Modifier.width(AppSpacing.extraSmallMedium)
                    )

                    Text(
                        text = "(${product.rating} reviews)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(
                    modifier = Modifier.height(AppSpacing.medium)
                )

                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(
                    modifier = Modifier.height(AppSpacing.largeMedium)
                )

                HorizontalDivider()

                Spacer(
                    modifier = Modifier.height(AppSpacing.largeMedium)
                )

                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp
                )

                Spacer(
                    modifier = Modifier.height(AppSpacing.large)
                )

                Button(
                    onClick = {
                        // Add to cart
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(AppSpacing.extraMedium)
                ) {
                    Text(
                        text = "Add to Cart"
                    )
                }
            }
        }
    }
}