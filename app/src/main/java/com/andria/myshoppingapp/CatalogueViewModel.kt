package com.andria.myshoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andria.myshoppingapp.api.ClothesStoreApi
import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.model.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogueViewModel(
    private val api: ClothesStoreApi,
) : ViewModel() {

    //View
    private val _viewState = MutableStateFlow<ViewState<List<Product>>>(ViewState.Loading)
    val viewState: StateFlow<ViewState<List<Product>>> = _viewState

    // Data
    private val _catalogue = MutableStateFlow<List<Product>>(emptyList())

    init {
        fetchCatalogue()
        observeCatalogue()
    }

    fun fetchCatalogue() {
        viewModelScope.launch {
            val response = api.getCatalogue()

            if (response.isSuccessful) {
                _catalogue.value = response.body()?.products ?: emptyList()
            }
            else {
                _viewState.value = ViewState.Error(Error("Failed to fetch catalogue"))
            }
        }
    }

    private fun observeCatalogue() {
        viewModelScope.launch {
            _catalogue.collect { catalogueItems ->
                _viewState.value = if (catalogueItems.isEmpty()) {
                    ViewState.Loading
                } else {
                    ViewState.Success(catalogueItems)
                }
            }
        }
    }
}