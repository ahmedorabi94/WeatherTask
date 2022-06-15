package com.ahmedorabi.weatherapp.features.add_city

import com.ahmedorabi.weatherapp.core.db.WeatherDao
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.repo.RoomDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InRoomLocalDataSourceAddCity @Inject constructor(private val weatherDao: WeatherDao) :
    RoomDataSource {
    override suspend fun addCity(city: City) {
        weatherDao.insertCity(city)
    }

    override suspend fun getCities(): Flow<List<City>> {
        return flow {
            emit(weatherDao.getAllCities())
        }.flowOn(Dispatchers.IO)
    }
}