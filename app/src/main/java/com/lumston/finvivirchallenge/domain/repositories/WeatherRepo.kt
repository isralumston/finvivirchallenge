package com.lumston.finvivirchallenge.domain.repositories

import com.lumston.finvivirchallenge.data.model.WeatherInfo

interface WeatherRepo {
    suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherInfo
    suspend fun getLastSearchedWeatherInfo(): WeatherInfo?
}