package com.lumston.finvivirchallenge.framework.di.modules.datasources

import com.lumston.finvivirchallenge.data.sources.remote.network.apis.OpenWeatherApi
import com.lumston.finvivirchallenge.data.sources.remote.src.OpenWeatherApiImpl
import com.lumston.finvivirchallenge.data.sources.remote.src.WeatherRemoteDataSrc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSrcModule {
    @Singleton
    @Provides
    fun providesWeatherRemoteDataSrc(
        api: OpenWeatherApi
    ): WeatherRemoteDataSrc {
        return OpenWeatherApiImpl(api)
    }
}