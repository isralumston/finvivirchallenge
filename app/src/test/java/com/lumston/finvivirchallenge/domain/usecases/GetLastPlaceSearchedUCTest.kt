package com.lumston.finvivirchallenge.domain.usecases

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.domain.repositories.WeatherRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetLastPlaceSearchedUCTest {
    private lateinit var getLastPlaceSearchedUC: GetLastPlaceSearchedUC

    // Mocks
    private val weatherRepo: WeatherRepo = mockk()
    private val weatherInfo: WeatherInfo = mockk()

    @Before
    fun setup() {
        getLastPlaceSearchedUC = GetLastPlaceSearchedUC(weatherRepo)
    }

    @Test
    fun `getLastPlaceSearched call, returns result from repo`() = runTest {
        // GIVEN
        coEvery { weatherRepo.getLastSearchedWeatherInfo() } returns weatherInfo

        // WHEN
        val result = getLastPlaceSearchedUC()

        // THEN
        coVerify(exactly = 1) { weatherRepo.getLastSearchedWeatherInfo() }
        assert(result == weatherInfo)
    }
}