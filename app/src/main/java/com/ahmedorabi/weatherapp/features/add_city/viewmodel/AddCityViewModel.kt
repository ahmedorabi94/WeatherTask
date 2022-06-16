package com.ahmedorabi.weatherapp.features.add_city.viewmodel

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
    private val getCitiesLocalUseCase: GetCitiesLocalUseCase,
    private val addCityUseCase: AddCityUseCase
) :
    ViewModel() {

    private val _citiesResponse = MutableLiveData<List<City>>()
    val citiesResponse: LiveData<List<City>>
        get() = _citiesResponse

     fun getCitiesResponseFlow() {
        viewModelScope.launch {
            getCitiesLocalUseCase.invoke()
                .collect { response ->
                    Timber.e(response.toString())
                    _citiesResponse.value = response
                }
        }

    }


    fun addCity(name : String){
        viewModelScope.launch {
            addCityUseCase.invoke(City(name = name))
        }
    }


}