package com.androidapp.myportfolioappandroid.feature.dashboard.presentation.model

import androidx.annotation.DrawableRes
import com.androidapp.myportfolioappandroid.core.ui.theme.GradientType

data class DashboardCardUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val route: String,
    @DrawableRes val imageRes: Int? = null,
    val gradientType: GradientType
)

