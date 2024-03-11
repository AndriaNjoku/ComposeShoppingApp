package com.andria.myshoppingapp

import com.andria.myshoppingapp.api.ClothesStoreApi
import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.model.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CatalogueViewModelTest {

    private lateinit var viewModel: CatalogueViewModel
    private val api: ClothesStoreApi = mockk()
    private val repository: ClothesRepository = mockk()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        viewModel = CatalogueViewModel(
            repository,
            testDispatcher
        )
    }


    @Test
    fun `fetchCatalogue returns success`() = runBlocking {
        val mockProducts = listOf(Product("Shirt", "shirt.jpg", 20.0, 10, "clothes", null, "1"))
        coEvery { repository.fetchClothes() } returns Result.success(mockProducts)

        viewModel.fetchCatalogue()

        assertEquals(ViewState.Success(mockProducts), viewModel.viewState.first())
    }

    @Test
    fun `fetchCatalogue returns error`() = runBlocking {
        val error = Error("Failed to fetch catalogue")
        coEvery { repository.fetchClothes() } returns Result.failure(error)

        viewModel.fetchCatalogue()

        assertEquals(ViewState.Error(error), viewModel.viewState.first())
    }
}
