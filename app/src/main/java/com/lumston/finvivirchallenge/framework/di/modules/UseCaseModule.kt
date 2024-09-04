package com.lumston.finvivirchallenge.framework.di.modules

import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import com.lumston.finvivirchallenge.domain.usecases.GetLastPlaceSearchedUC
import com.lumston.finvivirchallenge.domain.usecases.GetWeatherInfoUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providesGetWeatherInfoUC(
        weatherRepo: WeatherRepo
    ): GetWeatherInfoUC {
        return GetWeatherInfoUC(
            weatherRepo = weatherRepo
        )
    }

    @Provides
    fun providesGetLastWeatherSearchedUC(
        weatherRepo: WeatherRepo
    ): GetLastPlaceSearchedUC {
        return GetLastPlaceSearchedUC(
            weatherRepo = weatherRepo
        )
    }
}