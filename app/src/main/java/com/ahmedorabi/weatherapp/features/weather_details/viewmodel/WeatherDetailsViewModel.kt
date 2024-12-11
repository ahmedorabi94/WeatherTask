package com.ahmedorabi.weatherapp.features.weather_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.api.Resource
import com.example.core.domain.forecast.WeatherForecastResponse
import com.example.core.domain.model.HistoricalModel
import com.example.core.domain.model.WeatherResponse
import com.example.core.domain.usecases.AddHistoricalModelUseCase
import com.example.core.domain.usecases.GetCitiesUseCase
import com.example.core.domain.usecases.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val addHistoricalModelUseCase: AddHistoricalModelUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) :
    ViewModel() {
    var isSaveHistoricalModel = false

    private val _citiesResponse = MutableLiveData<Resource<WeatherResponse>>()
    val citiesResponse: LiveData<Resource<WeatherResponse>>
        get() = _citiesResponse


    private val _forecastState = MutableStateFlow(WeatherForecastResponse())
    val forecastState: StateFlow<WeatherForecastResponse> = _forecastState


    fun getCitiesResponseFlow(name: String) {
        viewModelScope.launch {
            getCitiesUseCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    _citiesResponse.value = response
                }
        }

    }

    private fun getForecastResponseFlow(name: String) {
        viewModelScope.launch {
            getWeatherForecastUseCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    response.data?.let {
                        _forecastState.value = it
                    }

                }
        }

    }

    // Handle intents to update the state
    fun handleIntent(intent: GetForecastIntent) {
        when (intent) {
            is GetForecastIntent.GetForecastList -> {
                getForecastResponseFlow(intent.cityName)
            }
        }
    }

    fun addHistoricalModel(temp: Int, name: String, desc: String) {

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