package com.lumston.finvivirchallenge.framework.di.modules

import android.content.Context
import androidx.room.Room
import com.lumston.finvivirchallenge.data.sources.local.database.FinvivirDatabase
import com.lumston.finvivirchallenge.data.sources.local.database.daos.WeatherInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context : Context): FinvivirDatabase {
        return Room.databaseBuilder(
            context, FinvivirDatabase::class.java, FinvivirDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesWeatherInfoDao(database: FinvivirDatabase): WeatherInfoDao {
        return database.weatherInfoDao()
    }
}