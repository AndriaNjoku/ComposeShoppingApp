package com.andria.myshoppingapp

import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.usecase.FetchCatalogueUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchCatalogueUseCaseTest {

    private lateinit var useCase: FetchCatalogueUseCase
    private val mockRepository = mockk<ClothesRepository>()

    @Before
    fun setup() {
        useCase = FetchCatalogueUseCase(mockRepository)
    }

    @Test
    fun `fetch catalogue returns expected result`() = runBlocking {
        val expected = Result.success(emptyList<Product>())

        coEvery { mockRepository.fetchClothes() } returns expected

        val result = useCase.invoke()

        assertEquals(
            expected,
            result)
    }
}