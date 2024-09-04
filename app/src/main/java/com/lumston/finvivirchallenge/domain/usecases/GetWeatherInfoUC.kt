package com.lumston.finvivirchallenge.domain.usecases

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import javax.inject.Inject

class GetWeatherInfoUC @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend operator fun invoke(
        lat: Double, lon: Double
    ): WeatherInfo {
        return weatherRepo.getWeatherInfo(lat, lon)
    }
}