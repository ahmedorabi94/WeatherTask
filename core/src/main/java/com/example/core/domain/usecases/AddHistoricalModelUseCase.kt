package com.example.core.domain.usecases

import com.example.core.domain.model.HistoricalModel
import com.example.core.repo.RoomRepository
import javax.inject.Inject

class AddHistoricalModelUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(historicalModel: HistoricalModel) =
        roomRepository.addHistoricalModel(historicalModel)
}