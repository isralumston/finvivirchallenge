package com.lumston.finvivirchallenge.data.model

data class WeatherInfo(
    val name: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val weather: Weather? = null,
    val conditions: List<Condition> = emptyList()
)
