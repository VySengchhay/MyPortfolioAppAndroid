package com.androidapp.myportfolioappandroid.feature.dashboard.presentation.model

import androidx.annotation.DrawableRes
import androidx.navigation3.runtime.NavKey
import com.androidapp.myportfolioappandroid.core.ui.theme.GradientType

data class DashboardCardUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val destination: NavKey,
    @DrawableRes val imageRes: Int? = null,
    val gradientType: GradientType
)

