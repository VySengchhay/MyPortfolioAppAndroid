package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.product.ProductResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(): List<ProductResponseDto>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductResponseDto
}