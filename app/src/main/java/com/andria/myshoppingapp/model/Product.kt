package com.andria.myshoppingapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "name") val name: String,
    @Json(name = "image") val image: String,
    @Json(name = "price") val price: Double,
    @Json(name = "stock") val stock: Int,
    @Json(name = "category") val category: String,
    @Json(name = "oldPrice") val oldPrice: Double?,
    @Json(name = "productId") val productId: String,
    var quantity: Int = 1 // Add this line

)

@JsonClass(generateAdapter = true)
data class ProductList(
    @Json(name = "products") val products: List<Product>
)