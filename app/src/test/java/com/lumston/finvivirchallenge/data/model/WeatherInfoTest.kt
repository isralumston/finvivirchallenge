package com.lumston.finvivirchallenge.data.model

import org.junit.Test

class WeatherInfoTest {
    @Test
    fun `data class copy properties test`() {
        // GIVEN
        val weatherInfo = WeatherInfo(
            place = "Zapopan",
            lat = 12.4, lon = 12.6,
            temp = 26f, weather = "Rain",
            weatherDescription = "Heavy rain",
            icon = "icon"
        )

        // WHEN
        val copy = weatherInfo.copy()

        // THEN
        assert(copy == weatherInfo)
    }

    @Test
    fun `weather info to entity`() {
        // GIVEN
        val weatherInfo = WeatherInfo(
            place = "Zapopan",
            lat = 12.4, lon = 12.6,
            temp = 26f, weather = "Rain",
            weatherDescription = "Heavy rain",
            icon = "icon"
        )

        // WHEN
        val entity = weatherInfo.toEntity()

        // THEN
        assert(
            weatherInfo.place == entity.place &&
            weatherInfo.lat == entity.lat &&
            weatherInfo.lon == entity.lon &&
            weatherInfo.temp == entity.temp &&
            weatherInfo.weather == entity.weather &&
            weatherInfo.weatherDescription == entity.weatherDescription &&
            weatherInfo.icon == entity.icon
        )
    }
}