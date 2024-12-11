package com.ahmedorabi.weatherapp.features.weather_details.framework

import com.example.core.api.ApiService
import com.example.core.api.Resource
import com.example.core.api.ResultWrapper
import com.example.core.api.safeApiCall
import com.example.core.domain.forecast.WeatherForecastResponse
import com.example.core.domain.model.WeatherResponse
import com.example.core.repo.WeatherDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ApiCitiesListDataSource @Inject constructor(private val apiService: ApiService) :
    WeatherDataSource {


    override suspend fun getWeatherResponse(name : String): Flow<Resource<WeatherResponse>> {

        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getWeatherResponseAsync(name)
            }

            when (response) {
                is ResultWrapper.Success -> {
                    emit(Resource.success(response.value))
                }
                is ResultWrapper.Error -> {
                    emit(Resource.error(response.error?.message ?: "Unknown Error"))

                }
                is ResultWrapper.NetworkError -> {
                    emit(Resource.error("NetworkError"))

                }
                ResultWrapper.NoContentError -> {
                    emit(Resource.error("NoContentError"))

                }
            }

        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getForecastWeatherResponse(name: String): Flow<Resource<WeatherForecastResponse>> {
        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getForecastWeatherResponseAsync(name)
            }

            when (response) {
                is ResultWrapper.Success -> {
                    emit(Resource.success(response.value))
                }
                is ResultWrapper.Error -> {
                    emit(Resource.error(response.error?.message ?: "Unknown Error"))

                }
                is ResultWrapper.NetworkError -> {
                    emit(Resource.error("NetworkError"))

                }
                ResultWrapper.NoContentError -> {
                    emit(Resource.error("NoContentError"))

                }
            }

        }.flowOn(Dispatchers.IO)
    }


}