package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazyrowlayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun LazyRowLayoutScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    FeatureScaffold(
        modifier = modifier,
        title = "Lazy Row",
        onBackClick = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(AppSpacing.medium)
        ) {
            ProjectCarousel(
                projects = listOf(
                    Project(
                        id = 1,
                        title = "Project 1",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = 2,
                        title = "Project 2",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = 3,
                        title = "Project 3",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = 4,
                        title = "Project 4",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = 5,
                        title = "Project 5",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = 6,
                        title = "Project 6",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                )
            )
        }
    }
}

@Composable
fun ProjectCarousel(
    projects: List<Project>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = AppSpacing.screenHorizontal),
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.medium)
    ) {
        items(
            items = projects,
            key = { it.id }
        ) { project ->
            ProjectCard(project = project)
        }
    }
}

@Composable
fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(AppSpacing.medium),
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .width(220.dp)
            .height(140.dp)
    ) {
        Column(
            modifier = Modifier.padding(AppSpacing.medium),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.extraSmall)
        ) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

data class Project(
    val id: Int,
    val title: String,
    val description: String
)