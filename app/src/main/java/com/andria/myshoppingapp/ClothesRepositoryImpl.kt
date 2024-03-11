package com.andria.myshoppingapp

import com.andria.myshoppingapp.api.ClothesStoreApi
import com.andria.myshoppingapp.model.Product
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val api: ClothesStoreApi,
) : ClothesRepository {

    override suspend fun fetchClothes(): Result<List<Product>> {
        val response = api.getCatalogue()
        return if (response.isSuccessful) {
            Result.success(response.body()?.products?: emptyList())
        } else {
            Result.failure(Exception("Error fetching clothes"))
        }
    }
}