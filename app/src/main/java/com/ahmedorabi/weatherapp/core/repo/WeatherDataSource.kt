package com.ahmedorabi.weatherapp.core.repo

import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.core.domain.forecast.WeatherForecastResponse
import com.ahmedorabi.weatherapp.core.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    suspend fun  getWeatherResponse(name : String): Flow<Resource<WeatherResponse>>

    suspend fun  getForecastWeatherResponse(name : String): Flow<Resource<WeatherForecastResponse>>

}