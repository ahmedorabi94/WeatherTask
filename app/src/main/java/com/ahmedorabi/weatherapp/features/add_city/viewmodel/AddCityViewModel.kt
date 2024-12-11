package com.ahmedorabi.weatherapp.features.add_city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.City
import com.example.core.domain.usecases.AddCityUseCase
import com.example.core.domain.usecases.GetCitiesLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCityViewModel @Inject constructor(
    getCitiesLocalUseCase: GetCitiesLocalUseCase,
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