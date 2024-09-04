package com.lumston.finvivirchallenge.data.sources.local.database.entities

import org.junit.Test

class WeatherInfoEntityTest {
    @Test
    fun `weather entity to model`() {
        // GIVEN
        val entity = WeatherInfoEntity(
            id = 1,
            place = "Place",
            lat = 23.3,
            lon = 65.4,
            temp = 13f,
            weather = "Rain",
            weatherDescription = "Rainy day",
            icon = "icon"
        )

        // WHEN
        val weatherInfo = entity.toModel()

        // THEN
        assert(
            weatherInfo.place == entity.place &&
            weatherInfo.lat == entity.lat &&
            weatherInfo.lon == entity.lon &&
            weatherInfo.temp == entity.temp &&
            weatherInfo.weather == entity.weather &&
            weatherInfo.weatherDescription == entity.weatherDescription &&
            weatherInfo.icon == entity.icon &&
            entity.id == 1
        )
    }
}