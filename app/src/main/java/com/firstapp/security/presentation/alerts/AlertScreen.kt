package com.firstapp.security.presentation.alerts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstapp.security.presentation.alerts.components.AlertData
import com.firstapp.security.presentation.alerts.components.fetchAlertsFromServer

// Screen en la que se muestran todos los AudioItem donde encontramos los datos del sensor PIR
@Composable
fun AlertScreen() {

    var alerts by remember { mutableStateOf<List<AlertData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            alerts = fetchAlertsFromServer()
        } catch (e: Exception) {
            error = e.message
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        modifier = Modifier.background(Color(0xFF121C2B)), containerColor = Color(0xFF121C2B),
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize().background(Color(0xFF121C2B))) {

            Text(
                modifier = Modifier.padding(top = 5.dp, start = 20.dp),
                text = "Alertas de Movimiento",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )



            when {
                isLoading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                errorMessage != null -> {
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(Modifier.fillMaxSize().padding(start = 16.dp, top =27.dp)) {
                        items(alerts) { alert ->
                            Card(Modifier.fillMaxWidth().padding(8.dp)) {
                                Column(Modifier.padding(8.dp)) {
                                    Text("Sensor: ${alert.sensor} Entrada")
                                    Text("Hora: ${alert.timestamp}")
                                    Icon(
                                        imageVector = Icons.Default.Details,
                                        contentDescription = "Alerta",
                                        tint = Color.Red,
                                        modifier = Modifier.size(32.dp)
                                            .padding(start = 10.dp,top = 10.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
