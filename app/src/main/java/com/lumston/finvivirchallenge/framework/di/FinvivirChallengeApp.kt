package com.lumston.finvivirchallenge.framework.di

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.lumston.finvivirchallenge.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FinvivirChallengeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (!Places.isInitialized()) {
            Places.initialize(
                this,
                BuildConfig.MAPS_KEY
            );
        }
    }
}