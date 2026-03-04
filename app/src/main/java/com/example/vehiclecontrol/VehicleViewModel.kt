package com.example.vehiclecontrol

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VehicleViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VehicleRepository(application)
    private val _carSpeed = MutableStateFlow(0f)
    val carSpeed = _carSpeed.asStateFlow()
    private val _targetTemp = MutableStateFlow(22.0f)
    val targetTemp = _targetTemp.asStateFlow()

    init {
        repository.connect()
    }

    fun adjustTemperature(delta: Float) {
        val newTemp = _targetTemp.value + delta
        if (newTemp in 16.0..30.0) {
            _targetTemp.value = newTemp
            repository.setTemperature(newTemp)
        }
    }

    fun updateSpeed(newSpeed: Float) {
        _carSpeed.value = newSpeed
    }
}