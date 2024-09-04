package com.lumston.finvivirchallenge.data.model

data class WeatherInfo(
    val name: String = "",
    val weather: Weather? = null,
    val conditions: List<Condition> = emptyList()
)
