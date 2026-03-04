package com.example.vehiclecontrol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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

@Composable
fun SpeedometerSection(speed: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${speed.toInt()}",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 100.sp,
                fontWeight = FontWeight.Bold,
                color = if (speed > 100) Color.Red else Color.Green
            )
        )
        Text(
            text = "KM/H",
            style = MaterialTheme.typography.labelLarge,
            color = Color.LightGray
        )
    }
}

@Composable
fun HvacControlSection(viewModel: VehicleViewModel) {
    val currentTemp by viewModel.targetTemp.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ControlButton(label = "-", color = Color(0xFF2196F3)) {
            viewModel.adjustTemperature(-0.5f)
        }

        Text(
            text = "${"%.1f".format(currentTemp)}°C",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        )

        ControlButton(label = "+", color = Color(0xFFF44336)) {
            viewModel.adjustTemperature(0.5f)
        }
    }
}

@Composable
fun ControlButton(label: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(90.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text = label, fontSize = 32.sp, fontWeight = FontWeight.Bold)
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