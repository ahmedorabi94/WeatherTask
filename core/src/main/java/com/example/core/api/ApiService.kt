package com.example.core.api

import com.example.core.domain.forecast.WeatherForecastResponse
import com.example.core.domain.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("weather?appid=f5cb0b965ea1564c50c6f1b74534d823")
    suspend fun getWeatherResponseAsync(@Query("q") q: String): WeatherResponse


//https://api.openweathermap.org/data/2.5/forecast?daily?cnt=7&appid=f5cb0b965ea1564c50c6f1b74534d823&q=cairo

    @GET("forecast?daily?cnt=7&appid=f5cb0b965ea1564c50c6f1b74534d823")
    suspend fun getForecastWeatherResponseAsync(@Query("q") q: String): WeatherForecastResponse


    //  forecast/daily?cnt=7&appid=f5cb0b965ea1564c50c6f1b74534d823
}