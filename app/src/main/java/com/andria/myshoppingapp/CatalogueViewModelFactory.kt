package com.andria.myshoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andria.myshoppingapp.api.ClothesStoreApi

class CatalogueViewModelFactory(private val api: ClothesStoreApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogueViewModel::class.java)) {
            return CatalogueViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}