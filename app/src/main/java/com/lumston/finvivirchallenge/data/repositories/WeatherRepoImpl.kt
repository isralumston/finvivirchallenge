package com.lumston.finvivirchallenge.data.repositories

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.data.sources.remote.src.WeatherRemoteDataSrc
import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val remoteSrc: WeatherRemoteDataSrc
): WeatherRepo {
    override suspend fun getWeatherInfo(
        lat: Double, lon: Double
    ): WeatherInfo {
        return remoteSrc.getWeatherInfo(lat, lon)
    }
}