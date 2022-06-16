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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val addHistoricalModelUseCase: AddHistoricalModelUseCase
) :
    ViewModel() {
    var isSaveHistoricalModel = false

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

    fun addHistoricalModel(temp : Int, name : String,desc : String, ) {

        // dd-MM-yyyy
        val df = SimpleDateFormat("dd.MM.yyyy - hh:mm", Locale.US)
        val time: String = df.format(Date())

        val historicalModel = HistoricalModel(
            name = name,
            desc = desc,
            temp = temp,
            dateTime = time
        )

        viewModelScope.launch {
            addHistoricalModelUseCase.invoke(historicalModel)
        }
    }
}