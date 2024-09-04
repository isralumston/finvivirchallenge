package com.lumston.finvivirchallenge.data.sources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lumston.finvivirchallenge.data.sources.local.database.daos.WeatherInfoDao
import com.lumston.finvivirchallenge.data.sources.local.database.entities.WeatherInfoEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [WeatherInfoEntity::class]
)
abstract class FinvivirDatabase: RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao

    companion object {
        const val DATABASE_NAME = "finvivir_database"
    }
}