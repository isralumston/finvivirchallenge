package com.lumston.finvivirchallenge.data.model

import org.junit.Test

class WeatherTest {
    @Test
    fun `data class copy properties test`() {
        // GIVEN
        val weather = Weather(temp = 27.6f)

        // WHEN
        val copy = weather.copy()

        // THEN
        assert(copy == weather)
    }
}