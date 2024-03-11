package com.andria.myshoppingapp

import com.andria.myshoppingapp.model.Product

interface ClothesRepository {
    suspend fun fetchClothes():  Result<List<Product>>
}