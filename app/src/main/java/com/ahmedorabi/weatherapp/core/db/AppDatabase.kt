package com.ahmedorabi.weatherapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedorabi.weatherapp.core.domain.model.City

@Database(entities = [City::class], version = 1, exportSchema = false)
//@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}