package com.ahmedorabi.weatherapp.core.domain.usecases

import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import com.ahmedorabi.weatherapp.core.repo.RoomRepository
import javax.inject.Inject

class AddHistoricalModelUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(historicalModel: HistoricalModel) =
        roomRepository.addHistoricalModel(historicalModel)
}