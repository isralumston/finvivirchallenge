package com.lumston.finvivirchallenge.framework.extensions

import android.view.View
import com.lumston.finvivirchallenge.R
import com.lumston.finvivirchallenge.data.model.WeatherInfo

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun WeatherInfo.iconToLocalResource(): Int {
    return when {
        // Clear weather
        icon.contains("01") -> R.drawable.ic_weather_clear
        // Cloudy
        icon.contains("02") ||
        icon.contains("03") ||
        icon.contains("04") -> R.drawable.ic_weather_clouds
        // Rain
        icon.contains("09") ||
        icon.contains("10") -> R.drawable.ic_weather_precipitation
        // Storm
        icon.contains("11") -> R.drawable.ic_weather_storm
        // Snow
        icon.contains("13") -> R.drawable.ic_weather_snow
        // Mist
        icon.contains("50") -> R.drawable.ic_weather_clouds
        // Default
        else -> R.drawable.ic_weather_clear
    }
}