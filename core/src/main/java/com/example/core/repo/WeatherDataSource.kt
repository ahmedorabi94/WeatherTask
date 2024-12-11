package com.example.core.repo

import com.example.core.api.Resource
import com.example.core.domain.forecast.WeatherForecastResponse
import com.example.core.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    suspend fun  getWeatherResponse(name : String): Flow<Resource<WeatherResponse>>

    suspend fun  getForecastWeatherResponse(name : String): Flow<Resource<WeatherForecastResponse>>

}