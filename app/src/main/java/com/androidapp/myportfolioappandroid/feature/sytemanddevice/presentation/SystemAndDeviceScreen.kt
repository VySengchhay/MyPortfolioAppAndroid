package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.androidapp.myportfolioappandroid.core.ui.component.TopAppBarCategory

@Composable
fun SystemAndDeviceScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onFeatureClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCategory(
                title = "Device & System",
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
                    text = "Device & System"
                )
            }
        }
    }
}