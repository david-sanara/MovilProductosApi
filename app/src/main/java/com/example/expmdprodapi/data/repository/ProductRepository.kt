package com.example.expmdprodapi.data.repository

import com.example.expmdprodapi.data.model.ProductDto
import com.example.expmdprodapi.data.remote.ProductApi

class ProductRepository(
    private val api: ProductApi
) {
    suspend fun getProducts(): List<ProductDto> =
        api.getProducts().results
}