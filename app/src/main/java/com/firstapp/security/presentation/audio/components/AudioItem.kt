package com.firstapp.security.presentation.audio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// Card en la que se encuentran todos los datos del sensor PIR una vez recogidos de la nube
@Composable
fun AudioItem(audio: AudioData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Micrófono",
                tint = Color(0xFF1E88E5),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("🎤 Mic: ${audio.mic ?: "Desconocido"}", style = MaterialTheme.typography.titleMedium)
                Text("🔊 Nivel: ${audio.level ?: "-"}", style = MaterialTheme.typography.bodyMedium)
                Text("📉 dBFS: ${audio.dbfs ?: "-"}", style = MaterialTheme.typography.bodySmall)
                Text("🕒 ${audio.timestamp}", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}



