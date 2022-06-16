package com.ahmedorabi.weatherapp.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historical")
data class HistoricalModel(
    @PrimaryKey(autoGenerate = true)
    val id:  Int = 0,
    val name : String,
    val temp: Int,
    val desc: String,
    val dateTime: String
)
