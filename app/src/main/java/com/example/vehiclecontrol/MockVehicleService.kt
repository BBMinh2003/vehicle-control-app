package com.example.vehiclecontrol

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MockVehicleService : Service() {

    private var currentTemp = 22.0f
    private val windowStates = mutableMapOf(0 to 0, 1 to 0)

    private val binder = object : IVehicleHardwareInterface.Stub() {
        override fun getHvacTemperature(): Float = currentTemp

        override fun setHvacTemperature(temp: Float) {
            currentTemp = temp
            Log.d("VehicleService", "Car temperature changed to: $temp")
        }

        override fun getWindowStatus(windowId: Int): Int = windowStates[windowId] ?: 0

        override fun setWindowStatus(windowId: Int, position: Int) {
            windowStates[windowId] = position
        }
    }

    override fun onBind(intent: Intent?): IBinder = binder
}