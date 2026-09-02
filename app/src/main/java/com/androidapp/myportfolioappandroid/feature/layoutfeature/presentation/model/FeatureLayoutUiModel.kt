package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.model

import androidx.annotation.DrawableRes
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory

data class FeatureLayoutUiModel(
    val id: String,
    val title: String,
    val description: String,
    val category: FeatureCategory,
    val route: String,
    @DrawableRes val imageRes: Int? = null,
)
