package com.example.core.domain.usecases

import com.example.core.domain.model.City
import com.example.core.repo.RoomRepository
import javax.inject.Inject

class AddCityUseCase @Inject constructor(private val roomRepository: RoomRepository){

    suspend operator fun invoke(city: City) = roomRepository.addCity(city)
}