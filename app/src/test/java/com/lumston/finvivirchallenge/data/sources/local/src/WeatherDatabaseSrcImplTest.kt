package com.lumston.finvivirchallenge.data.sources.local.src

import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.data.model.toEntity
import com.lumston.finvivirchallenge.data.sources.local.database.daos.WeatherInfoDao
import com.lumston.finvivirchallenge.data.sources.local.database.entities.WeatherInfoEntity
import com.lumston.finvivirchallenge.data.sources.local.database.entities.toModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeatherDatabaseSrcImplTest {
    private lateinit var weatherDatabaseSrcImpl: WeatherDatabaseSrcImpl

    // Mocks
    private val dao: WeatherInfoDao = mockk()
    private val weatherInfo: WeatherInfo = mockk()
    private val weatherInfoEntity: WeatherInfoEntity = mockk()

    @Before
    fun setup() {
        weatherDatabaseSrcImpl = WeatherDatabaseSrcImpl(dao)
    }

    @Test
    fun `weather db impl, successfully calls insert on dao`() = runTest {
        // GIVEN
        mockkStatic(WeatherInfo::toEntity)
        every { weatherInfo.toEntity() } returns weatherInfoEntity
        coEvery { dao.insert(weatherInfoEntity) } returns Unit

        // WHEN
        weatherDatabaseSrcImpl.storeLastWeatherInfo(weatherInfo)

        //THEN
        coVerify(exactly = 1) {
            dao.insert(weatherInfoEntity)
        }
    }

    @Test
    fun `weather db impl, successfully returns `() = runTest {
        // GIVEN
        mockkStatic(WeatherInfoEntity::toModel)
        coEvery { dao.queryFirst() } returns weatherInfoEntity
        every { weatherInfoEntity.toModel() } returns weatherInfo

        // WHEN
        val result = weatherDatabaseSrcImpl.retrieveLastWeatherInfo()

        //THEN
        assert(result == weatherInfo)
    }
}