package com.androidapp.myportfolioappandroid.feature.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DashBoardScreen(
    onProfileClick: () -> Unit,
    onRowLayoutClick: (String) -> Unit,
    onColumnLayoutClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dashboard Screen",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = {
                onProfileClick()
            }
        ) {
            Text(
                text = "Profile"
            )
        }

        Button(
            onClick = {
                onRowLayoutClick("rowlayout")
            }
        ) {
            Text(
                text = "Row Layout"
            )
        }

        Button(
            onClick = {
                onColumnLayoutClick("columnlayout")
            }
        ) {
            Text(
                text = "Column Layout"
            )
        }
    }
}