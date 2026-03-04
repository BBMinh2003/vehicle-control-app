package com.example.vehiclecontrol.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vehiclecontrol.viewmodel.VehicleViewModel
import kotlin.text.format

@Composable
fun HvacControlSection(viewModel: VehicleViewModel) {
    val currentTemp by viewModel.targetTemp.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier.Companion.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        ControlButton(label = "-", color = Color(0xFF2196F3)) {
            viewModel.adjustTemperature(-0.5f)
        }

        Text(
            text = "${"%.1f".format(currentTemp)}°C",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 48.sp,
                color = Color.Companion.White,
                fontWeight = FontWeight.Companion.ExtraBold
            )
        )

        ControlButton(label = "+", color = Color(0xFFF44336)) {
            viewModel.adjustTemperature(0.5f)
        }
    }
}