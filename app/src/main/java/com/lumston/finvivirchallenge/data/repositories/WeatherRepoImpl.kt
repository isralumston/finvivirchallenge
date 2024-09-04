package com.lumston.finvivirchallenge.data.repositories

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.data.sources.local.src.WeatherLocalDataSrc
import com.lumston.finvivirchallenge.data.sources.remote.src.WeatherRemoteDataSrc
import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val remoteSrc: WeatherRemoteDataSrc,
    private val localSrc: WeatherLocalDataSrc
): WeatherRepo {
    override suspend fun getWeatherInfo(
        lat: Double, lon: Double
    ): WeatherInfo {
        val info = remoteSrc.getWeatherInfo(lat, lon)
        localSrc.storeLastWeatherInfo(info)
        return info
    }

    override suspend fun getLastSearchedWeatherInfo(): WeatherInfo? {
        return localSrc.retrieveLastWeatherInfo()
    }
}