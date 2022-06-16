package com.ahmedorabi.weatherapp.features.weather_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import com.ahmedorabi.weatherapp.core.domain.model.WeatherResponse
import com.ahmedorabi.weatherapp.core.domain.usecases.AddHistoricalModelUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val addHistoricalModelUseCase: AddHistoricalModelUseCase
) :
    ViewModel() {

    private val _citiesResponse = MutableLiveData<Resource<WeatherResponse>>()
    val citiesResponse: LiveData<Resource<WeatherResponse>>
        get() = _citiesResponse


    fun getCitiesResponseFlow(name: String) {
        viewModelScope.launch {
            getCitiesUseCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    _citiesResponse.value = response
                }
        }

    }

    fun addHistoricalModel(historicalModel: HistoricalModel) {
        viewModelScope.launch {
            addHistoricalModelUseCase.invoke(historicalModel)
        }
    }
}