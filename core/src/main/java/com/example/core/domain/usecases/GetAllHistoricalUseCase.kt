package com.example.core.domain.usecases

import com.example.core.repo.RoomRepository
import javax.inject.Inject

class GetAllHistoricalUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(name : String) = roomRepository.getAllHistoricalModel(name)
}