package com.lumston.finvivirchallenge.data.sources.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lumston.finvivirchallenge.data.model.WeatherInfo

@Entity(tableName = "last_weather_info")
data class WeatherInfoEntity(
    @PrimaryKey
    val id: Int = 1,
    val place: String,
    val lat: Double,
    val lon: Double,
    val temp: Float,
    val weather: String,
    val weatherDescription: String,
    val icon: String
)

fun WeatherInfoEntity.toModel(): WeatherInfo {
    return WeatherInfo(
        place = place,
        lat = lat,
        lon = lon,
        temp = temp,
        weather = weather,
        weatherDescription = weatherDescription,
        icon = icon
    )
}