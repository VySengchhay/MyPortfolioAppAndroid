package com.androidapp.myportfolioappandroid.feature.apifeature.data.repository

import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.ProductApiService
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.mapper.toDomain
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.ProductRepository
import jakarta.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
) : ProductRepository {
    override suspend fun getAllProduct(): AppResult<List<Product>> {
        return try {
            val products = productApiService.getAllProducts().map { it.toDomain() }
            AppResult.Success(products)
        } catch (e: Exception) {
            AppResult.Error(AppError.Unknown(e))
        }
    }
}