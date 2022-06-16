package com.ahmedorabi.weatherapp.features.add_city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.usecases.AddCityUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetCitiesLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val getCitiesLocalUseCase: GetCitiesLocalUseCase,
    private val addCityUseCase: AddCityUseCase
) :
    ViewModel() {


    val allCities = getCitiesLocalUseCase.invoke()

    fun addCity(name: String) {
        viewModelScope.launch {
            addCityUseCase.invoke(City(name = name))
        }
    }


}