package com.andria.myshoppingapp.di

import com.andria.myshoppingapp.CatalogueViewModelFactory
import com.andria.myshoppingapp.ClothesRepository
import com.andria.myshoppingapp.ClothesRepositoryImpl
import com.andria.myshoppingapp.api.ClothesStoreApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideClothesStoreApi(): ClothesStoreApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.npoint.io/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(ClothesStoreApi::class.java)
    }

    @Provides
    fun provideClothesRepository(api: ClothesStoreApi): ClothesRepository {
        return ClothesRepositoryImpl(api)
    }

    @Provides
    fun provideCatalogueViewModelFactory(
        repo: ClothesRepository,
    ): CatalogueViewModelFactory {
        return CatalogueViewModelFactory(
            repo,
            Dispatchers.Main
        )
    }
}