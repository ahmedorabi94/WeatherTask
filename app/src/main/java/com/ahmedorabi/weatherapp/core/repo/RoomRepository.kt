package com.ahmedorabi.weatherapp.core.repo

import com.ahmedorabi.weatherapp.core.domain.model.City
import javax.inject.Inject

class RoomRepository @Inject constructor(private val roomDataSource: RoomDataSource) {

    suspend fun addCity(city: City) = roomDataSource.addCity(city)
    suspend fun getCities() = roomDataSource.getCities()
}