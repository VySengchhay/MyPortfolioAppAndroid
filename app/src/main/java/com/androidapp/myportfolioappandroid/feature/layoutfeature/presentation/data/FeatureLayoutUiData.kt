package com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.model.FeatureLayoutUiModel
import com.androidapp.myportfolioappandroid.navigation.BoxLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.ColumnLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.HorizontalPagerLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.LazyColumnLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.LazyHorizontalGridLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.LazyRowLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.LazyVerticalGridLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.RowLayoutRoute
import com.androidapp.myportfolioappandroid.navigation.VerticalPagerLayoutRoute
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
            destination = RowLayoutRoute,
            imageRes = R.drawable.im_row_layout,
        ),
        FeatureLayoutUiModel(
            id = "LF2",
            title = "Column",
            description = "Arrange composable items vertically from top to bottom with control over spacing, alignment, and distribution.",
            category = FeatureCategory.LAYOUT,
            destination = ColumnLayoutRoute,
            imageRes = R.drawable.im_column_layout,
        ),
        FeatureLayoutUiModel(
            id = "LF3",
            title = "Box",
            description = "Stack and position composable items on top of each other with customizable alignment.",
            category = FeatureCategory.LAYOUT,
            destination = BoxLayoutRoute,
            imageRes = R.drawable.im_box,
        ),
        FeatureLayoutUiModel(
            id = "LF4",
            title = "Lazy Row",
            description = "Display a horizontally scrolling list of items efficiently by composing only the items currently needed.",
            category = FeatureCategory.LAYOUT,
            destination = LazyRowLayoutRoute,
            imageRes = R.drawable.im_lazy_row_layout,
        ),
        FeatureLayoutUiModel(
            id = "LF5",
            title = "Lazy Column",
            description = "Display a vertically scrolling list of items efficiently by composing only the items currently needed.",
            category = FeatureCategory.LAYOUT,
            destination = LazyColumnLayoutRoute,
            imageRes = R.drawable.im_lazy_column_layout,
        ),
        FeatureLayoutUiModel(
            id = "LF6",
            title = "Lazy Vertical Grid",
            description = "Arrange composable items in a vertically scrolling grid with multiple columns, efficiently displaying large collections of items.",
            category = FeatureCategory.LAYOUT,
            destination = LazyVerticalGridLayoutRoute,
            imageRes = R.drawable.im_lazy_vertical_grid,
        ),
        FeatureLayoutUiModel(
            id = "LF7",
            title = "Lazy Horizontal Grid",
            description = "Arrange composable items in a horizontally scrolling grid with multiple rows, efficiently displaying large collections of items.",
            category = FeatureCategory.LAYOUT,
            destination = LazyHorizontalGridLayoutRoute,
            imageRes = R.drawable.im_lazy_horizontal_grid,
        ),
        FeatureLayoutUiModel(
            id = "LF8",
            title = "Horizontal Pager",
            description = "Display composable items as pages that can be swiped horizontally from left to right, with support for controlling the current page and page scrolling.",
            category = FeatureCategory.LAYOUT,
            destination = HorizontalPagerLayoutRoute,
            imageRes = R.drawable.im_horizontal_pager
        ),
        FeatureLayoutUiModel(
            id = "LF9",
            title = "Vertical Pager",
            description = "Display composable items as pages that can be swiped vertically from top to bottom, with support for controlling the current page and page scrolling.",
            category = FeatureCategory.LAYOUT,
            destination = VerticalPagerLayoutRoute,
            imageRes = R.drawable.im_vertical_pager
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
