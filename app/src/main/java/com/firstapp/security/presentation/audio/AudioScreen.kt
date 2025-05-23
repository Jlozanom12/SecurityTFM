package com.firstapp.security.presentation.audio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstapp.security.presentation.audio.components.AudioData
import com.firstapp.security.presentation.audio.components.AudioItem
import com.firstapp.security.presentation.audio.components.fetchAudioEventsFromServer
import kotlinx.coroutines.launch



// Screen en la que se muestran todas las alertas del sensor PIR con forma de Card
@Composable
fun AudioScreen() {
    val scope = rememberCoroutineScope()
    var events by remember { mutableStateOf<List<AudioData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                events = fetchAudioEventsFromServer()
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(modifier = Modifier.background(Color(0xFF121C2B)),containerColor = Color(0xFF121C2B),
        /*
        topBar = {
            TopAppBar(modifier = Modifier.background(Color(0xFF121C2B)), title = { Text(text="Eventos de Audio", color = Color.White,fontWeight = FontWeight.Bold,fontSize = 25.sp) })
        }

         */
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize().background(Color(0xFF121C2B))) {

            Text(modifier = Modifier.padding(top = 5.dp,start =20.dp),text="Eventos de Audio", color = Color.White,fontWeight = FontWeight.Bold,fontSize = 25.sp)
            Spacer(modifier = Modifier.padding(top = 10.dp))
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
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(36.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(events) { event ->
                            AudioItem(event)
                        }
                    }
                }
            }
        }
    }
}
