package com.lumston.finvivirchallenge.data.sources.local.src

import com.lumston.finvivirchallenge.data.model.WeatherInfo

interface WeatherLocalDataSrc {
    suspend fun storeLastWeatherInfo(info: WeatherInfo)
    suspend fun retrieveLastWeatherInfo(): WeatherInfo?
}