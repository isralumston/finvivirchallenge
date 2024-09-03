package com.lumston.finvivirchallenge.framework.di.modules

import com.lumston.finvivirchallenge.framework.coroutines.DispatcherProvider
import com.lumston.finvivirchallenge.framework.coroutines.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Singleton
    @Provides
    fun providesDispatchers(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}