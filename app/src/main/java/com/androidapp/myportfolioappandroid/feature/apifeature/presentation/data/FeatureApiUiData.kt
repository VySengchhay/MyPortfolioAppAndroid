package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.model.FeatureApiUiModel
import com.androidapp.myportfolioappandroid.feature.layoutfeature.presentation.model.FeatureLayoutUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeatureApiUiData @Inject constructor() {
    private val featureApiUiDataList: List<FeatureApiUiModel> = listOf<FeatureApiUiModel>(
        FeatureApiUiModel(
            id = "FA1",
            title = "User",
            description = "Pick and display a single photo from your device using the Android Photo Picker.",
            category = FeatureCategory.API,
            route = "api_user_route",
            imageRes = R.drawable.im_user,
        )
    )

    fun getFeatureApiUiModelList(
         category: FeatureCategory
    ): Flow<List<FeatureApiUiModel>> {
        return flow {
            delay(500)
            val filterFeatures = featureApiUiDataList.filter {
                it.category == category
            }
            emit(filterFeatures)
        }
    }
}