package com.androidapp.myportfolioappandroid.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FeatureScaffold(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    floatingActionButton: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCategory(
                title = title,
                onBackClick = onBackClick
            )
        },
        floatingActionButton = floatingActionButton,
    ) { innerPadding ->
        content(innerPadding)
    }
}