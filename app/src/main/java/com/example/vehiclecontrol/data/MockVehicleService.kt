package com.example.vehiclecontrol.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.vehiclecontrol.IVehicleHardwareInterface
import com.example.vehiclecontrol.utils.VehicleEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

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

    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())

    override fun onCreate() {
        super.onCreate()
        simulateVehicleData()
    }

    private fun simulateVehicleData() {
        serviceScope.launch {
            var currentSpeed = 0
            while (isActive) {
                delay(2000)
                currentSpeed = (0..120).random()

                val intent = Intent(VehicleEvents.ACTION_SPEED_CHANGED).apply {
                    putExtra(VehicleEvents.EXTRA_VALUE, currentSpeed.toFloat())
                    setPackage(packageName)
                }
                sendBroadcast(intent)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder = binder
}