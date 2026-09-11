package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.data

import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.state.FeatureCategory
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.model.FeatureApiUiModel
import com.androidapp.myportfolioappandroid.navigation.ProductRoute
import com.androidapp.myportfolioappandroid.navigation.TaskRoomDbRoute
import com.androidapp.myportfolioappandroid.navigation.UserApiRoute
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
            destination = UserApiRoute,
            imageRes = R.drawable.im_user,
        ),
        FeatureApiUiModel(
            id = "FA2",
            title = "Task",
            description = "Create, store, update, and delete tasks using Room Database.",
            category = FeatureCategory.API,
            destination = TaskRoomDbRoute,
            imageRes = R.drawable.im_task,
        ),
        FeatureApiUiModel(
            id = "FA3",
            title = "Product",
            description = "Fetch and display product data from the Fake Store API using Retrofit.\"",
            category = FeatureCategory.API,
            destination = ProductRoute,
            imageRes = R.drawable.im_bag,
        ),
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
