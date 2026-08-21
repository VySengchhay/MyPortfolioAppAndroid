package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.model

import androidx.annotation.DrawableRes

data class FeatureUiModel(
    val id: String,
    val title: String,
    val description: String,
    val category: FeatureCategory,
    val route: String,
    @DrawableRes val imageRes: Int? = null,
)
