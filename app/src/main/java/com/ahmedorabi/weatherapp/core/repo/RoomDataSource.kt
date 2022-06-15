package com.ahmedorabi.weatherapp.core.repo

import com.ahmedorabi.weatherapp.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface RoomDataSource {

   suspend fun addCity(city: City)
    suspend fun getCities() : Flow<List<City>>
}