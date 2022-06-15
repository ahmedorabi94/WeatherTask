package com.ahmedorabi.weatherapp.core.domain.usecases

import com.ahmedorabi.weatherapp.core.repo.WeatherRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {


    suspend operator fun invoke(name : String) = weatherRepository.getWeatherResponse(name)

}