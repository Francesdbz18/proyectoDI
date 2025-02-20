package com.chesco.proyecto_di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class TemperatureViewModel : ViewModel() {
    var temperatureCelsius by mutableFloatStateOf(0f)
        private set
    var isFahrenheit by mutableStateOf(false)
        private set
    var listTemperatures = mutableStateListOf<Int>()
        private set

    fun updateTemperature(value: Float) {
        temperatureCelsius = value
    }

    fun toggleFahrenheit() {
        isFahrenheit = !isFahrenheit
    }

    fun saveTemperature() {
        if (listTemperatures.size >= 50) {
            listTemperatures.removeAt(0)
        }
        listTemperatures.add(temperatureCelsius.toInt())
    }
}