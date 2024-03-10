package com.andria.myshoppingapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyShoppingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize things here if needed
    }
}