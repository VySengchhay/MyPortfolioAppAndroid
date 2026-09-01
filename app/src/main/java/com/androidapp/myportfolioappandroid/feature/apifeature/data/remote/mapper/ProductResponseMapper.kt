package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.mapper

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.product.ProductResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product

fun ProductResponseDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = rating.rate,
        count = rating.count
    )
}