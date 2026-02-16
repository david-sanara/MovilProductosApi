package com.example.expmdprodapi.data.model

import com.squareup.moshi.Json

data class ProductDto(
    @Json(name = "_id") val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean


)
