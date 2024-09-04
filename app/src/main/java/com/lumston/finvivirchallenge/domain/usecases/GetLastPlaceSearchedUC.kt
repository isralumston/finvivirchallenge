package com.lumston.finvivirchallenge.domain.usecases

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import javax.inject.Inject

class GetLastPlaceSearchedUC @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend operator fun invoke(): WeatherInfo? {
        return weatherRepo.getLastSearchedWeatherInfo()
    }
}