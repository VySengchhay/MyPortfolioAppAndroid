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
            id = "LF3",
            title = "Box",
            description = "Pick and display a single photo from your device using the Android Photo Picker.",
            category = FeatureCategory.LAYOUT,
            route = "box_route",
            imageRes = R.drawable.im_box,
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
