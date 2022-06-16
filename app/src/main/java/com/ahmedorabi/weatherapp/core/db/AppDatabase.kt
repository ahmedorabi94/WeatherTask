package com.ahmedorabi.weatherapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel

@Database(entities = [City::class,HistoricalModel::class], version = 1, exportSchema = false)
//@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}