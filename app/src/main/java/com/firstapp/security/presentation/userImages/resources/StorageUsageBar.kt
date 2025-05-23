package com.firstapp.security.presentation.userImages.resources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StorageUsageBar(storageUsed: Float) {
    val totalStorage = 5f
    val progress = storageUsed / totalStorage

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 5.dp, start = 10.dp),
            text = "Galería de Imágenes",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Almacenamiento usado: ${"%.2f".format(storageUsed)} GB de 5 GB",
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = if (progress < 0.8f) Color.Yellow else Color.Red, // Rojo si supera 80%
            trackColor = Color.LightGray
        )
    }
}