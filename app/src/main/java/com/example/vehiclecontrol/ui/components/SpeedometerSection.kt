package com.example.vehiclecontrol.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SpeedometerSection(speed: Float) {
    Column(horizontalAlignment = Alignment.Companion.CenterHorizontally) {
        Text(
            text = "${speed.toInt()}",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 100.sp,
                fontWeight = FontWeight.Companion.Bold,
                color = if (speed > 100) Color.Companion.Red else Color.Companion.Green
            )
        )
        Text(
            text = "KM/H",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Companion.LightGray
        )
    }
}