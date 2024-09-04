package com.lumston.finvivirchallenge.data.repositories

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.data.sources.local.src.WeatherLocalDataSrc
import com.lumston.finvivirchallenge.data.sources.remote.src.WeatherRemoteDataSrc
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeatherRepoImplTest {
    private lateinit var weatherRepoImpl: WeatherRepoImpl

    // Mocks
    private val remoteSrc: WeatherRemoteDataSrc = mockk()
    private val localSrc: WeatherLocalDataSrc = mockk()
    private val weatherInfo: WeatherInfo = mockk()
    private val lat = 89.9
    private val lon = 13.54

    @Before
    fun setup() {
        weatherRepoImpl = WeatherRepoImpl(
            remoteSrc, localSrc
        )
    }

    @Test
    fun `repo getWeatherInfo, successfully calls remote data src`() = runTest {
        // GIVEN
        coEvery { remoteSrc.getWeatherInfo(lat, lon) } returns weatherInfo
        coEvery { localSrc.storeLastWeatherInfo(any()) } returns Unit

        // WHEN
        val result = weatherRepoImpl.getWeatherInfo(lat, lon)

        // THEN
        assert(result == weatherInfo)
    }

    @Test
    fun `repo getWeatherInfo, stores data on local src`() = runTest {
        // GIVEN
        coEvery { remoteSrc.getWeatherInfo(lat, lon) } returns weatherInfo
        coEvery { localSrc.storeLastWeatherInfo(weatherInfo) } returns Unit

        // WHEN
        weatherRepoImpl.getWeatherInfo(lat, lon)

        // THEN
        coVerify(exactly = 1) { localSrc.storeLastWeatherInfo(weatherInfo) }
    }

    @Test
    fun `repo getLastSearchedWeatherInfo, calls local data src`() = runTest {
        // GIVEN
        coEvery { localSrc.retrieveLastWeatherInfo() } returns weatherInfo

        // WHEN
        val result = weatherRepoImpl.getLastSearchedWeatherInfo()

        // THEN
        assert(result == weatherInfo)
    }
}