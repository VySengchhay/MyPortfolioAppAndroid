package com.androidapp.myportfolioappandroid.core.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCategory(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(
                onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarCategoryPreview() {
    MyPortfolioAppAndroidTheme() {
        TopAppBarCategory(
            title = "Device & System",
            onBackClick = {},
        )
    }
}
