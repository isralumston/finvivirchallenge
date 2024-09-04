package com.lumston.finvivirchallenge.data.sources.remote.src

import com.lumston.finvivirchallenge.BuildConfig
import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.data.sources.remote.network.apis.OpenWeatherApi
import javax.inject.Inject

class OpenWeatherApiImpl @Inject constructor(
    private val api: OpenWeatherApi
): WeatherRemoteDataSrc {
    override suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherInfo {
        val request = api.getWeather(
            lat = lat, lon = lon,
            appId = BuildConfig.WEATHER_KEY
        )

        return if (request.isSuccessful) {
            val response = request.body()!!
            WeatherInfo(
                name = response.name,
                weather = response.main,
                conditions = response.weather
            )
        } else WeatherInfo()
    }
}