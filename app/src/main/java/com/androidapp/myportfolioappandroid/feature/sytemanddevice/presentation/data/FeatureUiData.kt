package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
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
            id = "SD2",
            title = "Single video pick",
            description = "Pick and display a single video from your device using the Android Video Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            route = "single_video_pick_route",
            imageRes = R.drawable.im_mul_video,
        ),
        FeatureUiModel(
            id = "DS3",
            title = "Multiple photo pick",
            description = "Pick and display a multiple photo from your device using the Android Photo Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            route = "multiple_photo_pick_route",
            imageRes = R.drawable.im_photopick,
        ),
        FeatureUiModel(
            id = "DS4",
            title = "Multiple video pick",
            description = "Pick and display a multiple video from your device using the Android Video Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            route = "multiple_video_pick_route",
            imageRes = R.drawable.im_mul_video,
        ),
        FeatureUiModel(
            id = "DS5",
            title = "Select Photo & Image pick",
            description = "Pick and display a photo & video from your device using the Android Photo & Video Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            route = "photo_and_video_pick_route",
            imageRes = R.drawable.im_photo_video,
        ),
        FeatureUiModel(
            id = "DS6",
            title = "Camera Launcher",
            description = "Launch the device camera to capture a photo or video.",
            category = FeatureCategory.SYSTEM_DEVICE,
            route = "camera_launcher_route",
            imageRes = R.drawable.im_camera,
        ),
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
