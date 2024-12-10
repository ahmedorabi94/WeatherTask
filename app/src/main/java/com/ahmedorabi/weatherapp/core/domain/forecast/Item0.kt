package com.ahmedorabi.weatherapp.core.domain.forecast

import com.ahmedorabi.weatherapp.core.domain.model.Clouds
import com.ahmedorabi.weatherapp.core.domain.model.Sys
import com.ahmedorabi.weatherapp.core.domain.model.Wind

data class Item0(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Int,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)