package com.example.vehiclecontrol

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VehicleViewModel : ViewModel() {
    private val _carSpeed = MutableStateFlow(0f)
    val carSpeed = _carSpeed.asStateFlow()

    fun updateSpeed(newSpeed: Float) {
        _carSpeed.value = newSpeed
    }
}