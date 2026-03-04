package com.example.vehiclecontrol

import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.vehiclecontrol.data.VehicleReceiver
import com.example.vehiclecontrol.ui.dashboard.DashboardScreen
import com.example.vehiclecontrol.ui.theme.VehicleControlTheme
import com.example.vehiclecontrol.utils.VehicleEvents
import com.example.vehiclecontrol.viewmodel.VehicleViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: VehicleViewModel by viewModels()
    private lateinit var vehicleReceiver: VehicleReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VehicleControlTheme {
                DashboardScreen(viewModel = viewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        vehicleReceiver = VehicleReceiver(
            onSpeedUpdate = { speed -> viewModel.updateSpeed(speed) },
            onFuelWarning = {
                Log.d("VehicleApp", "Warning: Low fuel!")
            }
        )

        val filter = IntentFilter().apply {
            addAction(VehicleEvents.ACTION_SPEED_CHANGED)
            addAction(VehicleEvents.ACTION_FUEL_WARNING)
        }

        registerReceiver(vehicleReceiver, filter, Context.RECEIVER_EXPORTED)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(vehicleReceiver)
    }
}