package com.lumston.finvivirchallenge.framework.di.modules

import com.lumston.finvivirchallenge.data.repositories.WeatherRepoImpl
import com.lumston.finvivirchallenge.data.sources.remote.src.WeatherRemoteDataSrc
import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesWeatherRepo(
        remoteSrc: WeatherRemoteDataSrc
    ): WeatherRepo {
        return WeatherRepoImpl(remoteSrc)
    }
}