package com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product

interface ProductRepository {
    suspend fun getAllProduct(): AppResult<List<Product>>

    suspend fun getProductById(
        id: Int
    ): AppResult<Product>
}