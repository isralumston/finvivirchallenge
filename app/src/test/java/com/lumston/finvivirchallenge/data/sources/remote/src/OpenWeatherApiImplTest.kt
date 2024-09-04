package com.lumston.finvivirchallenge.data.sources.remote.src

import com.lumston.finvivirchallenge.data.sources.remote.network.apis.OpenWeatherApi
import com.lumston.finvivirchallenge.data.sources.remote.network.responses.WeatherResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class OpenWeatherApiImplTest {
    private lateinit var openWeatherApiImpl: OpenWeatherApiImpl

    // Mocks
    private val api: OpenWeatherApi = mockk()

    // Test data
    private val lat = 13.14
    private val lon = -236.26
    private val weatherResponse: WeatherResponse = mockk(relaxed = true)
    private val emptyResponse = "An error occurred".toResponseBody()

    @Before
    fun setup() {
        openWeatherApiImpl = OpenWeatherApiImpl(api)
    }

    @Test
    fun `remote data src impl, successfully calls api`() = runTest {
        // GIVEN
        coEvery { api.getWeather(lat, lon, any()) } returns Response
            .success(weatherResponse)

        // WHEN
        openWeatherApiImpl.getWeatherInfo(lat, lon)

        //THEN
        coVerify(exactly = 1) {
            api.getWeather(lat, lon, any())
        }
    }

    @Test
    fun `remote data src impl, error response returns lat & lon`() = runTest {
        // GIVEN
        coEvery { api.getWeather(lat, lon, any()) } returns Response
            .error(500, emptyResponse)

        // WHEN
        val result = openWeatherApiImpl.getWeatherInfo(lat, lon)

        //THEN
        assert(
            result.lat == lat &&
            result.lon == lon
        )
    }
}