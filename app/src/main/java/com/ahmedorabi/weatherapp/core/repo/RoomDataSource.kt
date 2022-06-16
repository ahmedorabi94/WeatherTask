package com.ahmedorabi.weatherapp.core.repo

import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import kotlinx.coroutines.flow.Flow

interface RoomDataSource {

    suspend fun addCity(city: City)
    suspend fun getCities(): Flow<List<City>>

    suspend fun addHistoricalModel(historicalModel: HistoricalModel)
    suspend fun getAllHistoricalModel(name : String): Flow<List<HistoricalModel>>
}