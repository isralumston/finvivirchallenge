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

        // Create WeatherInfo instance from response
        return if (request.isSuccessful && request.body() != null) {
            val response = request.body()!!
            WeatherInfo(
                place = response.name,
                lat = lat,
                lon = lon,
                temp = response.main.temp,
                weather = response.weather.let {
                    if (it.isNotEmpty()) it[0].main
                    else ""
                },
                weatherDescription = response.weather.let {
                    if (it.isNotEmpty()) it[0].description
                    else ""
                },
                icon = response.weather.let {
                    if (it.isNotEmpty()) it[0].icon
                    else ""
                }
            )
        } else WeatherInfo(
            lat = lat,
            lon = lon
        )
    }
}