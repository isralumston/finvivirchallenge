package com.lumston.finvivirchallenge.data.sources.local.src

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.data.model.toEntity
import com.lumston.finvivirchallenge.data.sources.local.database.daos.WeatherInfoDao
import com.lumston.finvivirchallenge.data.sources.local.database.entities.toModel
import javax.inject.Inject

class WeatherDatabaseSrcImpl @Inject constructor(
    private val dao: WeatherInfoDao
): WeatherLocalDataSrc {
    override suspend fun storeLastWeatherInfo(info: WeatherInfo) {
        dao.insert(info.toEntity())
    }

    override suspend fun retrieveLastWeatherInfo(): WeatherInfo? {
        return dao.queryFirst()?.toModel()
    }
}