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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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

        HvacControlSection()
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
fun HvacControlSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {

            },
            modifier = Modifier.size(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text("-", fontSize = 32.sp)
        }

        Text(text = "22°C", fontSize = 40.sp, color = Color.White)

        Button(
            onClick = {

            },
            modifier = Modifier.size(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text("+", fontSize = 32.sp)
        }
    }
}

@Preview(
    name = "Automotive Dashboard Landscape",
    device = "spec:width=1280dp,height=800dp,orientation=landscape",
    showBackground = true
)
@Composable
fun PreviewDashboard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SpeedometerSection(speed = 85f)
        Spacer(modifier = Modifier.height(20.dp))
        HvacControlSection()
    }
}