package com.lumston.finvivirchallenge.data.sources.remote.network.apis

import com.lumston.finvivirchallenge.data.sources.remote.network.responses.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String = "metric",
    ): Response<WeatherResponse>
}