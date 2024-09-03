package com.lumston.finvivirchallenge.data.sources.remote.src

import com.lumston.finvivirchallenge.data.model.WeatherInfo

interface WeatherRemoteDataSrc {
    suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherInfo
}