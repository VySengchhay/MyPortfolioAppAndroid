package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.horizontalpagerlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazyverticalgridlayout.Skill

@Composable
fun HorizontalPagerLayoutScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    FeatureScaffold(
        modifier = modifier,
        title = "Horizontal Pager",
        onBackClick = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(AppSpacing.medium)
        ) {
            ProjectImagePager(
                images = listOf(
                    R.drawable.im_food1,
                    R.drawable.im_food2,
                    R.drawable.im_food3,
                    R.drawable.im_food4,
                    R.drawable.im_food5,
                    R.drawable.im_food6,
                    R.drawable.im_food7,
                    R.drawable.im_food8,
                    R.drawable.im_food9,
                    R.drawable.im_food10,
                    R.drawable.im_food11,
                    R.drawable.im_food12,
                )
            )
        }
    }
}

@Composable
fun ProjectImagePager(
    images: List<Int>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            pageSpacing = AppSpacing.small
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Image(
                    painter = painterResource(images[page]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop

                )
            }
        }

        Spacer(modifier = Modifier.height(AppSpacing.small))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppSpacing.screenHorizontal),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = AppSpacing.extraExtraSmall)
                        .size(if (isSelected) AppSpacing.small else AppSpacing.extraSmall)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outlineVariant
                        )
                )
            }
        }
    }
}





