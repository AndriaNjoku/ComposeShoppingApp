package com.andria.myshoppingapp.di

import com.andria.myshoppingapp.CatalogueViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(catalogueViewModel: CatalogueViewModel)
}