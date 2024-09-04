package com.lumston.finvivirchallenge.domain.usecases

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetWeatherInfoUCTest {
    private lateinit var getWeatherInfoUC: GetWeatherInfoUC

    // Mocks
    private val weatherRepo: WeatherRepo = mockk()
    private val weatherInfo: WeatherInfo = mockk()
    private val lat = 19.78
    private val lon = 54.9

    @Before
    fun setup() {
        getWeatherInfoUC = GetWeatherInfoUC(weatherRepo)
    }

    @Test
    fun `getWeatherInfoUC call, returns successful result from repo`() = runTest {
        // GIVEN
        coEvery { weatherRepo.getWeatherInfo(lat, lon) } returns weatherInfo

        // WHEN
        val result = getWeatherInfoUC(lat, lon)

        // THEN
        coVerify(exactly = 1) { weatherRepo.getWeatherInfo(lat, lon) }
        assert(result == weatherInfo)
    }
}