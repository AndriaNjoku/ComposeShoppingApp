package com.andria.myshoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andria.myshoppingapp.CatalogueViewModel
import com.andria.myshoppingapp.ClothesRepository
import kotlinx.coroutines.CoroutineDispatcher

class CatalogueViewModelFactory(
    private val repo: ClothesRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogueViewModel::class.java)) {
            return CatalogueViewModel(repo, dispatcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
