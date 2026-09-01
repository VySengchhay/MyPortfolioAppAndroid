package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponseDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto
)

@Serializable
data class RatingDto(
    val rate: Double,
    val count: Int
)
