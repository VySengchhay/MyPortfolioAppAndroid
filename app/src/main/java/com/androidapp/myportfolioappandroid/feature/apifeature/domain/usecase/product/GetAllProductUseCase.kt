package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.product

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.product.Product
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): AppResult<List<Product>> {
        return productRepository.getAllProduct()
    }
}