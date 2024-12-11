package com.example.core.domain.usecases

import com.example.core.repo.RoomRepository
import javax.inject.Inject

class GetCitiesLocalUseCase @Inject constructor(private val roomRepository: RoomRepository) {

     operator fun invoke() = roomRepository.getCities()
}