package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.model.FeatureLayoutUiModel
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.model.FeatureUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeatureLayoutUiData @Inject constructor() {
    private val featureLayoutUiModelList: List<FeatureLayoutUiModel> = listOf(
        FeatureLayoutUiModel(
            id = "LF1",
            title = "Row",
            description = "Arrange and display composable items horizontally from left to right with control over spacing, alignment, and distribution.",
            category = FeatureCategory.LAYOUT,
            route = "row_route",
            imageRes = R.drawable.im_row_layout,
        ),
        FeatureLayoutUiModel(
            id = "LF2",
            title = "Column",
            description = "Arrange composable items vertically from top to bottom with control over spacing, alignment, and distribution.",
            category = FeatureCategory.LAYOUT,
            route = "column_route",
            imageRes = R.drawable.im_column_layout,
        ),
        FeatureLayoutUiModel(
            id = "LF3",
            title = "Box",
            description = "Stack and position composable items on top of each other with customizable alignment.",
            category = FeatureCategory.LAYOUT,
            route = "box_route",
            imageRes = R.drawable.im_box,
        ),
        FeatureLayoutUiModel(
            id = "LF4",
            title = "Lazy Row",
            description = "Display a horizontally scrolling list of items efficiently by composing only the items currently needed.",
            category = FeatureCategory.LAYOUT,
            route = "lazy_row_route",
            imageRes = R.drawable.im_lazy_row_layout,
        ),
    )
    
    suspend fun getFeatureLayoutUiModelList(
        category: FeatureCategory
    ): Flow<List<FeatureLayoutUiModel>> {
        delay(500)
        return flow {
            val filterFeatures = featureLayoutUiModelList.filter {
                it.category == category
            }
            emit(filterFeatures)
        }
    }
}
