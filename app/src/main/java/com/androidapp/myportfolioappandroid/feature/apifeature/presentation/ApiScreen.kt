package com.androidapp.myportfolioappandroid.feature.apifeature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.androidapp.myportfolioappandroid.core.ui.component.TopAppBarCategory

@Composable
fun ApiScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onFeatureClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCategory(
                title = "Api",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                Text(
                    text = "Api"
                )
            }
        }
    }
}