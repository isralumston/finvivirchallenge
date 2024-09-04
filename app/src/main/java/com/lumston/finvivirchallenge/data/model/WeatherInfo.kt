package com.lumston.finvivirchallenge.data.model

import androidx.annotation.DrawableRes
import com.lumston.finvivirchallenge.R

data class WeatherInfo(
    val place: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val temp: Float = 0f,
    val weather: String = "",
    val weatherDescription: String = "",
    @DrawableRes val icon: Int = R.drawable.ic_weather_clear
)
