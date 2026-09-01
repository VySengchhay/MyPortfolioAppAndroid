package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.product.ProductResponseDto
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(): List<ProductResponseDto>
}