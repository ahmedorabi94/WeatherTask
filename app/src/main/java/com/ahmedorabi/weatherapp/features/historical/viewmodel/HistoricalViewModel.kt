package com.ahmedorabi.weatherapp.features.historical.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import com.ahmedorabi.weatherapp.core.domain.usecases.AddCityUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetAllHistoricalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HistoricalViewModel @Inject constructor(
    private val getAllHistoricalUseCase: GetAllHistoricalUseCase,
) :
    ViewModel() {

    private val _citiesResponse = MutableLiveData<List<HistoricalModel>>()
    val citiesResponse: LiveData<List<HistoricalModel>>
        get() = _citiesResponse



    fun getCitiesResponseFlow(name: String) {
        viewModelScope.launch {
            getAllHistoricalUseCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    _citiesResponse.value = response
                }
        }

    }

}