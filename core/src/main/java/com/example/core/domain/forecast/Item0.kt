package com.example.core.domain.forecast

import com.example.core.domain.model.Clouds
import com.example.core.domain.model.Wind

data class Item0(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val sys: com.example.core.domain.model.Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)