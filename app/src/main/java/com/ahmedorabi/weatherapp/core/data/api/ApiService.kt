package com.ahmedorabi.weatherapp.core.data.api

import com.ahmedorabi.weatherapp.core.domain.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("weather?appid=f5cb0b965ea1564c50c6f1b74534d823")
    suspend fun getWeatherResponseAsync(@Query("q") q: String): WeatherResponse


}