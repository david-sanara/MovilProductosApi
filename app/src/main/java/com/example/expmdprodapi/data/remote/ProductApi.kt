package com.example.expmdprodapi.data.remote

import com.example.expmdprodapi.data.model.PagedResponse
import com.example.expmdprodapi.data.model.ProductDto
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): PagedResponse<ProductDto>

}