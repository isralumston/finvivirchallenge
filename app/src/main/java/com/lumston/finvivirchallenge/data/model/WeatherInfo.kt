package com.lumston.finvivirchallenge.data.model

import com.lumston.finvivirchallenge.data.sources.local.database.entities.WeatherInfoEntity

data class WeatherInfo(
    val place: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val temp: Float = 0f,
    val weather: String = "",
    val weatherDescription: String = "",
    val icon: String = ""
)

fun WeatherInfo.toEntity(): WeatherInfoEntity {
    return WeatherInfoEntity(
        place = place,
        lat = lat,
        lon = lon,
        temp = temp,
        weather = weather,
        weatherDescription = weatherDescription,
        icon = icon
    )
}