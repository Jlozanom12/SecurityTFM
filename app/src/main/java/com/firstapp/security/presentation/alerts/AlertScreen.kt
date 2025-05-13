package com.firstapp.security.presentation.alerts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.firstapp.security.presentation.alerts.components.AlertData
import com.firstapp.security.presentation.alerts.components.fetchAlertsFromServer

@Composable
fun AlertScreen() {
    var alerts by remember { mutableStateOf<List<AlertData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            alerts = fetchAlertsFromServer()
        } catch (e: Exception) {
            error = e.message
        } finally {
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $error")
            }
        }

        else -> {
            LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {
                items(alerts) { alert ->
                    Card(Modifier.fillMaxWidth().padding(8.dp)) {
                        Column(Modifier.padding(8.dp)) {
                            Text("Sensor: ${alert.sensor}")
                            Text("Hora: ${alert.timestamp}")
                        }
                    }
                }
            }
        }
    }
}
