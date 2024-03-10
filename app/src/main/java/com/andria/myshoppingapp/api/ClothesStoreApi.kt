package com.andria.myshoppingapp.api

import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.model.ProductList
import retrofit2.Response
import retrofit2.http.GET

interface ClothesStoreApi {
    @GET("0f78766a6d68832d309d")
    suspend fun getCatalogue(): Response<ProductList>
}