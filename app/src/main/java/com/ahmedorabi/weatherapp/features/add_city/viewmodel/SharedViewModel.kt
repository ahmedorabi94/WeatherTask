package com.ahmedorabi.weatherapp.features.add_city.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val isInsert = MutableLiveData<Boolean>()

    fun sendMessage(value: Boolean) {
        isInsert.value = value
    }

}