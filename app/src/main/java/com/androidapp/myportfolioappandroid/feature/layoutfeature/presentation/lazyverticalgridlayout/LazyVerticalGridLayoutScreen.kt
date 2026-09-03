package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.lazyverticalgridlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing

@Composable
fun LazyVerticalGridLayoutScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    FeatureScaffold(
        modifier = modifier,
        title = "Lazy Vertical Grid",
        onBackClick = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(AppSpacing.medium)
        ) {
            SkillsGridScreen(
                skills = listOf(
                    Skill(
                        id = "1",
                        name = "Kotlin",
                        iconRes = R.drawable.im_kotlin
                    ),
                    Skill(
                        id = "2",
                        name = "Java",
                        iconRes = R.drawable.im_java
                    ),
                    Skill(
                        id = "3",
                        name = "Python",
                        iconRes = R.drawable.im_python
                    ),
                    Skill(
                        id = "4",
                        name = "C++",
                        iconRes = R.drawable.im_cc
                    ),
                    Skill(
                        id = "5",
                        name = "C#",
                        iconRes = R.drawable.im_c
                    ),
                    Skill(
                        id = "6",
                        name = "JavaScript",
                        iconRes = R.drawable.im_js
                    ),
                    Skill(
                        id = "7",
                        name = "Kotlin",
                        iconRes = R.drawable.im_kotlin
                    ),
                    Skill(
                        id = "8",
                        name = "Java",
                        iconRes = R.drawable.im_java
                    ),
                    Skill(
                        id = "9",
                        name = "Python",
                        iconRes = R.drawable.im_python
                    ),
                    Skill(
                        id = "10",
                        name = "C++",
                        iconRes = R.drawable.im_cc
                    ),
                    Skill(
                        id = "11",
                        name = "C#",
                        iconRes = R.drawable.im_c
                    ),
                    Skill(
                        id = "12",
                        name = "JavaScript",
                        iconRes = R.drawable.im_js
                    ),
                    Skill(
                        id = "13",
                        name = "Kotlin",
                        iconRes = R.drawable.im_kotlin
                    ),
                    Skill(
                        id = "14",
                        name = "Java",
                        iconRes = R.drawable.im_java
                    ),
                    Skill(
                        id = "15",
                        name = "Python",
                        iconRes = R.drawable.im_python
                    ),
                    Skill(
                        id = "16",
                        name = "C++",
                        iconRes = R.drawable.im_cc
                    ),
                    Skill(
                        id = "17",
                        name = "C#",
                        iconRes = R.drawable.im_c
                    ),
                    Skill(
                        id = "18",
                        name = "JavaScript",
                        iconRes = R.drawable.im_js
                    ),
                    Skill(
                        id = "19",
                        name = "Kotlin",
                        iconRes = R.drawable.im_kotlin
                    ),
                    Skill(
                        id = "20",
                        name = "Java",
                        iconRes = R.drawable.im_java
                    ),
                    Skill(
                        id = "21",
                        name = "Python",
                        iconRes = R.drawable.im_python
                    ),
                    Skill(
                        id = "22",
                        name = "C++",
                        iconRes = R.drawable.im_cc
                    ),
                    Skill(
                        id = "23",
                        name = "C#",
                        iconRes = R.drawable.im_c
                    ),
                    Skill(
                        id = "24",
                        name = "JavaScript",
                        iconRes = R.drawable.im_js
                    ),
                )
            )
        }
    }
}

@Composable
fun SkillsGridScreen(
    skills: List<Skill>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = AppSpacing.screenHorizontal,
            vertical = AppSpacing.screenVertical
        ),
        horizontalArrangement = Arrangement.spacedBy(AppSpacing.medium),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.medium)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Tech Stack",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = AppSpacing.small)
            )
        }

        items(
            items = skills,
            key = { it.id }
        ) { skill ->
            SkillGridItem(skill = skill)
        }
    }
}

@Composable
fun SkillGridItem(
    skill: Skill,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppSpacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Image placeholder — swap this Box for
            // Image(...) or AsyncImage(...) once you have the icon/logo
            Box(
                modifier = Modifier
                    .size(AppSpacing.extraExtraLarge)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                 Image(
                     painter = painterResource(skill.iconRes),
                     contentDescription = skill.name,
                     modifier = Modifier.size(AppSpacing.large)
                 )
            }

            Spacer(modifier = Modifier.height(AppSpacing.extraSmall))

            Text(
                text = skill.name,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

data class Skill(
    val id: String,
    val name: String,
    val iconRes: Int
)



