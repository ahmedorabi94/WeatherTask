package com.ahmedorabi.weatherapp.core.repo

import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import javax.inject.Inject

class RoomRepository @Inject constructor(private val roomDataSource: RoomDataSource) {

    suspend fun addCity(city: City) = roomDataSource.addCity(city)
    fun getCities() = roomDataSource.getCities()

    suspend fun addHistoricalModel(historicalModel: HistoricalModel) =
        roomDataSource.addHistoricalModel(historicalModel)

    suspend fun getAllHistoricalModel(name: String) = roomDataSource.getAllHistoricalModel(name)
}