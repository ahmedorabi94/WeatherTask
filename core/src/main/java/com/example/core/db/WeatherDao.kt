package com.example.core.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.model.City
import com.example.core.domain.model.HistoricalModel

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoricalModel(historicalModel: HistoricalModel)

    @Query("Select * from cities")
    fun getAllCities(): LiveData<List<City>>

    @Query("Select * from historical where name =:city")
    fun getAllHistoricalModel(city: String): List<HistoricalModel>

}