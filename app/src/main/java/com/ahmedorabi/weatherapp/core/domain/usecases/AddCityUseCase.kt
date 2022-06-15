package com.ahmedorabi.weatherapp.core.domain.usecases

import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.repo.RoomRepository
import javax.inject.Inject

class AddCityUseCase @Inject constructor(private val roomRepository: RoomRepository){

    suspend operator fun invoke(city: City) = roomRepository.addCity(city)
}