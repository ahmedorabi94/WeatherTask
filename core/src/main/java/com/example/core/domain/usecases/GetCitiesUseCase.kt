package com.example.core.domain.usecases

import com.example.core.repo.WeatherRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {


    suspend operator fun invoke(name : String) = weatherRepository.getWeatherResponse(name)

}