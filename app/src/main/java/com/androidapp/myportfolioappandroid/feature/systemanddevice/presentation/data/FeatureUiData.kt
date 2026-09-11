package com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.systemanddevice.presentation.model.FeatureUiModel
import com.androidapp.myportfolioappandroid.navigation.CameraLauncherRoute
import com.androidapp.myportfolioappandroid.navigation.CameraXRoute
import com.androidapp.myportfolioappandroid.navigation.GetLocationRoute
import com.androidapp.myportfolioappandroid.navigation.MultiplePhotoPickRoute
import com.androidapp.myportfolioappandroid.navigation.MultipleVideoPickRoute
import com.androidapp.myportfolioappandroid.navigation.PhotoAndVideoPickRoute
import com.androidapp.myportfolioappandroid.navigation.SinglePhotoPickRoute
import com.androidapp.myportfolioappandroid.navigation.SingleVideoPickRoute
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
            destination = SinglePhotoPickRoute,
            imageRes = R.drawable.im_photopick,
        ),
        FeatureUiModel(
            id = "SD2",
            title = "Single video pick",
            description = "Pick and display a single video from your device using the Android Video Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            destination = SingleVideoPickRoute,
            imageRes = R.drawable.im_mul_video,
        ),
        FeatureUiModel(
            id = "DS3",
            title = "Multiple photo pick",
            description = "Pick and display a multiple photo from your device using the Android Photo Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            destination = MultiplePhotoPickRoute,
            imageRes = R.drawable.im_photopick,
        ),
        FeatureUiModel(
            id = "DS4",
            title = "Multiple video pick",
            description = "Pick and display a multiple video from your device using the Android Video Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            destination = MultipleVideoPickRoute,
            imageRes = R.drawable.im_mul_video,
        ),
        FeatureUiModel(
            id = "DS5",
            title = "Select Photo & Image pick",
            description = "Pick and display a photo & video from your device using the Android Photo & Video Picker.",
            category = FeatureCategory.SYSTEM_DEVICE,
            destination = PhotoAndVideoPickRoute,
            imageRes = R.drawable.im_photo_video,
        ),
        FeatureUiModel(
            id = "DS7",
            title = "Get Location",
            description = "Access the device's current location to determine its latitude and longitude using location services.",
            category = FeatureCategory.SYSTEM_DEVICE,
            destination = GetLocationRoute,
            imageRes = R.drawable.im_location,
        ),
        FeatureUiModel(
            id = "DS8",
            title = "CameraLauncher",
            description = "Capture and preview photos using the device camera with CameraLauncher",
            category = FeatureCategory.SYSTEM_DEVICE,
            destination = CameraLauncherRoute,
            imageRes = R.drawable.im_camera,
        ),
        FeatureUiModel(
            id = "DS9",
            title = "CameraX",
            description = "Capture and preview photos using the device camera with CameraX.",
            category = FeatureCategory.SYSTEM_DEVICE,
            destination = CameraXRoute,
            imageRes = R.drawable.im_camerax,
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
