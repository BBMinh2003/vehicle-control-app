package com.example.vehiclecontrol.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ControlButton(label: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.Companion.size(90.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text = label, fontSize = 32.sp, fontWeight = FontWeight.Companion.Bold)
    }
}