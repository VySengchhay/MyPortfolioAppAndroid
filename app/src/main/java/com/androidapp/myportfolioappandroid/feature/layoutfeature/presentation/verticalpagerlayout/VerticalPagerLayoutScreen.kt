package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.verticalpagerlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun VerticalPagerLayoutScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    FeatureScaffold(
        modifier = modifier,
        title = "Vertical Pager",
        onBackClick = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(AppSpacing.medium)
        ) {
            ProjectStoryPager(
                projects = listOf(
                    Project(
                        id = "1",
                        title = "Project 1",
                        description = "This is the first project.",
                        imageRes = R.drawable.im_bg1
                    ),
                    Project(
                        id = "2",
                        title = "Project 2",
                        description = "This is the second project.",
                        imageRes = R.drawable.im_bg2
                    ),
                    Project(
                        id = "3",
                        title = "Project 3",
                        description = "This is the third project.",
                        imageRes = R.drawable.im_bg3
                    ),
                    Project(
                        id = "4",
                        title = "Project 4",
                        description = "This is the fourth project.",
                        imageRes = R.drawable.im_bg4
                    ),
                    Project(
                        id = "5",
                        title = "Project 5",
                        description = "This is the fifth project.",
                        imageRes = R.drawable.im_bg5
                    ),
                    Project(
                        id = "6",
                        title = "Project 6",
                        description = "This is the sixth project.",
                        imageRes = R.drawable.im_bg6
                    ),
                    Project(
                        id = "7",
                        title = "Project 7",
                        description = "This is the seventh project.",
                        imageRes = R.drawable.im_bg7
                    ),
                    Project(
                        id = "8",
                        title = "Project 8",
                        description = "This is the eighth project.",
                        imageRes = R.drawable.im_bg8
                    ),
                )
            )
        }
    }
}

@Composable
fun ProjectStoryPager(
    projects: List<Project>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { projects.size })

    VerticalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        pageSpacing = AppSpacing.small
    ) { page ->
        val project = projects[page]
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppSpacing.screenHorizontal)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(project.imageRes),
                contentDescription = project.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Scrim for text legibility over the image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
            )

            Column(
                modifier = Modifier.padding(AppSpacing.medium),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.extraSmall)
            ) {
                Text(
                    text = project.title,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = project.description,
                    color = Color.White.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

data class Project(
    val id: String,
    val title: String,
    val description: String,
    val imageRes: Int
)



