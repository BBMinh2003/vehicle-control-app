package com.example.vehiclecontrol

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class VehicleReceiver(
    private val onSpeedUpdate: (Float) -> Unit,
    private val onFuelWarning: () -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            VehicleEvents.ACTION_SPEED_CHANGED -> {
                val speed = intent.getFloatExtra(VehicleEvents.EXTRA_VALUE, 0.0f)
                onSpeedUpdate(speed)
            }
            VehicleEvents.ACTION_FUEL_WARNING -> {
                onFuelWarning()
            }
        }
    }
}