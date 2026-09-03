package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazycolumnlayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
fun LazyColumnLayoutScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    FeatureScaffold(
        modifier = modifier,
        title = "Lazy Column",
        onBackClick = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(AppSpacing.medium)
        ) {
            ProjectListScreen(
                projects = listOf(
                    Project(
                        id = "1",
                        title = "Project 1",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "2",
                        title = "Project 2",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "3",
                        title = "Project 3",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "4",
                        title = "Project 4",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "5",
                        title = "Project 5",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "6",
                        title = "Project 6",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "7",
                        title = "Project 7",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "8",
                        title = "Project 8",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "9",
                        title = "Project 9",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "10",
                        title = "Project 10",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "11",
                        title = "Project 11",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "12",
                        title = "Project 12",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "13",
                        title = "Project 13",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                    Project(
                        id = "14",
                        title = "Project 14",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    ),
                ),
                onProjectClick = {
                    // Handle project click
                }
            )
        }
    }
}

@Composable
fun ProjectListScreen(
    projects: List<Project>,
    onProjectClick: (Project) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = AppSpacing.screenHorizontal,
            vertical = AppSpacing.screenVertical
        ),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.medium)
    ) {
        item {
            Text(
                text = "My Projects",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = AppSpacing.small)
            )
        }

        items(
            items = projects,
            key = { it.id }
        ) { project ->
            ProjectListItem(
                project = project,
                onClick = { onProjectClick(project) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(AppSpacing.extraLarge))
        }
    }
}

@Composable
fun ProjectListItem(
    project: Project,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
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
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

data class Project(
    val id: String,
    val title: String,
    val description: String
)