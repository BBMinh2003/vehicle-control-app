package com.example.vehiclecontrol

import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vehiclecontrol.ui.theme.VehicleControlTheme

class MainActivity : ComponentActivity() {
    private val viewModel: VehicleViewModel by viewModels()
    private lateinit var vehicleReceiver: VehicleReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VehicleControlTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        vehicleReceiver = VehicleReceiver(
            onSpeedUpdate = { speed -> viewModel.updateSpeed(speed) },
            onFuelWarning = { /* Show Alert */ }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VehicleControlTheme {
        Greeting("Android")
    }
}