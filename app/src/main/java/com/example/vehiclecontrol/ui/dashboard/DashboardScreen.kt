package com.example.vehiclecontrol.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vehiclecontrol.viewmodel.VehicleViewModel
import com.example.vehiclecontrol.ui.components.ControlButton
import com.example.vehiclecontrol.ui.components.HvacControlSection
import com.example.vehiclecontrol.ui.components.SpeedometerSection

@Composable
fun DashboardScreen(viewModel: VehicleViewModel) {
    val speed by viewModel.carSpeed.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SpeedometerSection(speed)

        HorizontalDivider(color = Color.Gray, thickness = 1.dp)

        HvacControlSection(viewModel)
    }
}

@Preview(
    name = "Automotive Dashboard Landscape",
    device = "spec:width=1280dp,height=800dp,orientation=landscape",
    showBackground = true
)

@Preview(device = "spec:width=1280dp,height=800dp,orientation=landscape")
@Composable
fun PreviewHMI() {
    MaterialTheme {
        Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SpeedometerSection(speed = 65f)
                Spacer(modifier = Modifier.height(50.dp))
                Text("HVAC Preview Mode", color = Color.Gray)
                Row {
                    ControlButton("-", Color.Blue) {}
                    Text("24.0°C", fontSize = 40.sp, color = Color.White)
                    ControlButton("+", Color.Red) {}
                }
            }
        }
    }
}