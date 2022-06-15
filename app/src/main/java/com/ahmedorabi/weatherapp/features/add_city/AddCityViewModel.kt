package com.ahmedorabi.weatherapp.features.add_city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.model.WeatherResponse
import com.ahmedorabi.weatherapp.core.domain.usecases.AddCityUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetCitiesLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val useCase: GetCitiesLocalUseCase,
    private val addRateUseCase: AddCityUseCase
) :
    ViewModel() {

    private val _ratesResponse = MutableLiveData<List<City>>()
    val ratesResponse: LiveData<List<City>>
        get() = _ratesResponse


    init {
        getRatesResponseFlow()
    }

    private fun getRatesResponseFlow() {
        viewModelScope.launch {
            useCase.invoke()
                .collect { response ->
                    Timber.e(response.toString())
                    _ratesResponse.value = response
                }
        }

    }


    fun addCity(name : String){
        viewModelScope.launch {
            addRateUseCase.invoke(City(name = name))
        }
    }


}