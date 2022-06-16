package com.ahmedorabi.weatherapp.features.add_city.framework

import com.ahmedorabi.weatherapp.core.db.WeatherDao
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
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

    override suspend fun addHistoricalModel(historicalModel: HistoricalModel) {
        weatherDao.insertHistoricalModel(historicalModel)
    }

    override suspend fun getAllHistoricalModel(name : String): Flow<List<HistoricalModel>> {
        return flow {
            emit(weatherDao.getAllHistoricalModel(name))
        }.flowOn(Dispatchers.IO)
    }
}