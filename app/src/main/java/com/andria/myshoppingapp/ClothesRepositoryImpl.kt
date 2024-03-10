package com.andria.myshoppingapp

import com.andria.myshoppingapp.api.ClothesStoreApi
import com.andria.myshoppingapp.model.Product
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val api: ClothesStoreApi,
) : ClothesRepository {

    override suspend fun fetchClothes(): List<Product>? {
        val response = api.getCatalogue()
        if (response.isSuccessful) {
            return response.body()?.products
        } else {
            throw Exception("Error fetching clothes")
        }
    }
}