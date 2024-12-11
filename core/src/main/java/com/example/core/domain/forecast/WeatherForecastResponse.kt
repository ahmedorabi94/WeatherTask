package com.example.core.domain.forecast

data class WeatherForecastResponse(
    val city: City?=null,
    val cnt: Int = 0,
    val cod: String = "",
    val list: List<Item0> = emptyList(),
    val message: Int = 0
)