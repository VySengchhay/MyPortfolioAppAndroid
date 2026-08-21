package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.model.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.model.FeatureUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeatureUiData @Inject constructor() {
    private val featureUiModelList: List<FeatureUiModel> = listOf(
        // System & device
        FeatureUiModel(
            id = "SD1",
            title = "Single photo pick",
            description = "Pick and display a single photo from your device using the Android Photo Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            route = "single_photo_pick_route",
            imageRes = R.drawable.im_photopick,
        ),
        FeatureUiModel(
            id = "DS2",
            title = "Multiple photo pick",
            description = "Reusable UI elements used to build consistent and interactive interfaces.",
            category = FeatureCategory.SYSTEM_DEVICE,
            route = "multiple_photo_pick_route",
            imageRes = R.drawable.im_device_system,
        ),


        // Layout
        FeatureUiModel(
            id = "LF1",
            title = "Row",
            description = "Connect your app to servers and external services to exchange data.",
            category = FeatureCategory.LAYOUT,
            route = "row_route",
            imageRes = R.drawable.im_layout,
        ),
        FeatureUiModel(
            id = "LF2",
            title = "Column",
            description = "Connect your app to servers and external services to exchange data.",
            category = FeatureCategory.LAYOUT,
            route = "column_route",
            imageRes = R.drawable.im_layout,
        )
    )
    
    suspend fun getFeatureUiModelList(
        category: FeatureCategory
    ): Flow<List<FeatureUiModel>> {
        delay(500)
        return flow {
            val filterFeatures = featureUiModelList.filter {
                it.category == category
            }
            emit(filterFeatures)
        }
    }
}
