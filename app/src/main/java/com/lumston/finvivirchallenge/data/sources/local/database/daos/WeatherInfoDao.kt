package com.lumston.finvivirchallenge.data.sources.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lumston.finvivirchallenge.data.sources.local.database.entities.WeatherInfoEntity

@Dao
interface WeatherInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherInfoEntity: WeatherInfoEntity)

    @Query("SELECT * FROM last_weather_info LIMIT 1")
    suspend fun queryFirst(): WeatherInfoEntity?
}