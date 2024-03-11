package com.andria.myshoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.model.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogueViewModel(
    private val repository: ClothesRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState<List<Product>>>(ViewState.Loading)
    val viewState: StateFlow<ViewState<List<Product>>> = _viewState

    init {
        fetchCatalogue()
    }

    fun fetchCatalogue() {
        viewModelScope.launch(dispatcher){
            val response = repository.fetchClothes()
            response.fold(
                onSuccess = { products ->
                    _viewState.value = ViewState.Success(products)
                },
                onFailure = { error ->
                    _viewState.value = ViewState.Error(error)
                }
            )
        }
    }
}