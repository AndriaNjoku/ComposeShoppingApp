package com.andria.myshoppingapp

import com.andria.myshoppingapp.CatalogueViewModel
import com.andria.myshoppingapp.model.ProductList
import com.andria.myshoppingapp.api.ClothesStoreApi
import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.model.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CatalogueViewModelTest {

    private lateinit var viewModel: CatalogueViewModel
    private val api: ClothesStoreApi = mockk()

    @Before
    fun setup() {
        viewModel = CatalogueViewModel(api)
    }

    @Test
    fun `fetchCatalogue returns success`() = runBlockingTest {
        val mockProducts = listOf(Product("Shirt", "shirt.jpg", 20.0, 10, "clothes", null, "1"))
        coEvery { api.getCatalogue() } returns Response.success(ProductList(mockProducts))

        viewModel.fetchCatalogue()

        assertEquals(mockProducts, viewModel.viewState.value)
        assertEquals(ViewState.Success(mockProducts), viewModel.viewState.first())
    }

    @Test
    fun `fetchCatalogue returns error`() = runBlockingTest {
        val error = Error("Failed to fetch catalogue")
        coEvery { api.getCatalogue() } throws error

        viewModel.fetchCatalogue()

        assertEquals(ViewState.Error(error), viewModel.viewState.first())
    }
}
