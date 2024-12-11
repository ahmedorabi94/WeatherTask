package com.example.core.repo

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val dataSource: WeatherDataSource) {

    suspend fun getWeatherResponse(name : String) = dataSource.getWeatherResponse(name)

    suspend fun getForecastWeatherResponse(name : String) = dataSource.getForecastWeatherResponse(name)

}