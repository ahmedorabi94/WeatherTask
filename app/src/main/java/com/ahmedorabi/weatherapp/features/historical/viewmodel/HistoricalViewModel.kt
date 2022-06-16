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
    private val useCase: GetAllHistoricalUseCase,
) :
    ViewModel() {

    private val _ratesResponse = MutableLiveData<List<HistoricalModel>>()
    val ratesResponse: LiveData<List<HistoricalModel>>
        get() = _ratesResponse


    init {
        //  getRatesResponseFlow()
    }

    fun getRatesResponseFlow(name: String) {
        viewModelScope.launch {
            useCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    _ratesResponse.value = response
                }
        }

    }

}