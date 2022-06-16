package com.ahmedorabi.weatherapp.core.domain.usecases

import com.ahmedorabi.weatherapp.core.repo.RoomRepository
import javax.inject.Inject

class GetAllHistoricalUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(name : String) = roomRepository.getAllHistoricalModel(name)
}