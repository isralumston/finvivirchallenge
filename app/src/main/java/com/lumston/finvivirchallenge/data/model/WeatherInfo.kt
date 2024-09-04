package com.lumston.finvivirchallenge.data.model

data class WeatherInfo(
    val place: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val temp: Float = 0f,
    val weather: String = "",
    val weatherDescription: String = "",
    val icon: String = ""
)
