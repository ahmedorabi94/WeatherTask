package com.ahmedorabi.weatherapp.core.repo

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val dataSource: WeatherDataSource) {

    suspend fun getWeatherResponse(name : String) = dataSource.getWeatherResponse(name)

}