package com.ahmedorabi.weatherapp.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedorabi.weatherapp.core.domain.model.City

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Query("Select * from cities")
    fun getAllCities(): List<City>


//    @Query("Select * from currency where created_at BETWEEN :to AND :currentDate")
//    fun getAllRates(currentDate: Date, to: Date): List<CurrencyDbModel>

}