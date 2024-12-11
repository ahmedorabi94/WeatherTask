package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.domain.model.City
import com.example.core.domain.model.HistoricalModel

@Database(entities = [City::class, HistoricalModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}