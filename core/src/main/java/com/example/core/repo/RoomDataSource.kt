package com.example.core.repo

import androidx.lifecycle.LiveData
import com.example.core.domain.model.City
import com.example.core.domain.model.HistoricalModel
import kotlinx.coroutines.flow.Flow

interface RoomDataSource {

    suspend fun addCity(city: City)
    fun getCities(): LiveData<List<City>>

    suspend fun addHistoricalModel(historicalModel: HistoricalModel)
    suspend fun getAllHistoricalModel(name: String): Flow<List<HistoricalModel>>
}