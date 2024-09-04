package com.lumston.finvivirchallenge.presentation.main

import com.lumston.finvivirchallenge.data.model.WeatherInfo

interface MainViewContract {
    fun onWeatherInfoRequested()
    fun onWeatherInfoReady(info: WeatherInfo, lat: Double, lon: Double)
}