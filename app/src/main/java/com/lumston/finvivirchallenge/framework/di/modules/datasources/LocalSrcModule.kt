package com.lumston.finvivirchallenge.framework.di.modules.datasources

import com.lumston.finvivirchallenge.data.sources.local.database.daos.WeatherInfoDao
import com.lumston.finvivirchallenge.data.sources.local.src.WeatherDatabaseSrcImpl
import com.lumston.finvivirchallenge.data.sources.local.src.WeatherLocalDataSrc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSrcModule {
    @Singleton
    @Provides
    fun providesWeatherLocalDataSrc(
        dao: WeatherInfoDao
    ): WeatherLocalDataSrc {
        return WeatherDatabaseSrcImpl(dao)
    }
}