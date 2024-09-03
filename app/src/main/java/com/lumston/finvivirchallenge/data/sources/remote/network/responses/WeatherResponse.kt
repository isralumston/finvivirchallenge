package com.lumston.finvivirchallenge.data.sources.remote.network.responses

import com.lumston.finvivirchallenge.data.model.Condition
import com.lumston.finvivirchallenge.data.model.Weather

data class WeatherResponse(
    val id: Long,
    val name: String,
    val timezone: Int,
    val main: Weather,
    val weather: List<Condition>
)
