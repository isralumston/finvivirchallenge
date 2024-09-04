package com.lumston.finvivirchallenge.data.sources.remote.network.responses

import com.lumston.finvivirchallenge.data.model.Weather
import org.junit.Test

class WeatherResponseTest {
    @Test
    fun `data class copy properties test`() {
        // GIVEN
        val weatherResponse = WeatherResponse(
            id = 1,
            name = "Place",
            timezone = 27888,
            main = Weather(temp = 25f),
            weather = emptyList()
        )

        // WHEN
        val copy = weatherResponse.copy()

        // THEN
        assert(copy == weatherResponse)
    }
}