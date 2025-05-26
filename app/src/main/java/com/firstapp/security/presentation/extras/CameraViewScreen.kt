package com.firstapp.security.presentation.extras

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.firstapp.security.presentation.cams.uploadSnapshot



// Screen que se muestra al hacer click en la card de la cámara.
// Se muestra el streaming de vídeo y permite haceer fotos.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraViewScreen() {
    val webViewRef = remember { mutableStateOf<WebView?>(null) }

    // Dirección del stream MJPEG desde la ESP32-CAM
    val cameraUrl = "http://192.168.10.37:8080/stream"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cámara en vivo", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF121C2B),// Azul oscuro tirando a gris

                        titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    webViewRef.value?.let { webView ->
                        // Crea un nuevo objeto Bitmap (una representación de la imagen en memoria).
                        val bitmap = Bitmap.createBitmap(
                            webView.width,
                            webView.height,
                            Bitmap.Config.ARGB_8888
                        )
                        // El objeto canvas dibujará sobre el Bitmap que acabamos de crear.
                        val canvas = Canvas(bitmap)
                        webView.draw(canvas)

                        // Llamada a la API para subir la imagen al servidor.
                        uploadSnapshot(bitmap, "http://50.17.211.163:5000/upload")
                    }
                    Log.d("CameraViewScreen", "Floating Action Button presionado")},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 86.dp)
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Capturar imagen")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF121C2B))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Streaming MJPEG
                AndroidView(
                    factory = {
                        WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            settings.javaScriptEnabled = true
                            loadUrl(cameraUrl)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(2.dp, Color.White, RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = "Última transmisión activa",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )

                Icon(
                    imageVector = Icons.Default.Wifi,
                    contentDescription = "Conectado",
                    tint = Color.Green,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    )
}




