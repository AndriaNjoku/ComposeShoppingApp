package com.andria.myshoppingapp.usecase

import com.andria.myshoppingapp.ClothesRepository
import com.andria.myshoppingapp.model.Product
import javax.inject.Inject

class FetchCatalogueUseCase @Inject constructor(
    private val repository: ClothesRepository
) {
    suspend operator fun invoke(): List<Product>? = repository.fetchClothes()
}