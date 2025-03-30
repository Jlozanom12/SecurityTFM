package com.firstapp.security.cams

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import com.firstapp.security.R
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.firstapp.security.models.Routes


@Composable
fun StreamingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(200.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),
            onClick = { navController.navigate(Routes.CameraViewScreen.routes) }
        ) {

        }
        val context = LocalContext.current
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(200.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),
            onClick = {
                Toast.makeText(context, "No hay cámara disponible", Toast.LENGTH_SHORT).show()
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column( // Organiza la imagen arriba y el texto abajo
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.c_maratachada),
                        contentDescription = "Sin señal",
                        modifier = Modifier.size(150.dp) // Ajusta el tamaño de la imagen
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Espacio entre imagen y texto
                    Text(
                        text = "NO SIGNAL",
                        fontSize = 20.sp,
                        color = Color.Red
                    )
                }
            }
        }
    }
}



@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CameraStreaming() {
    val streamUrl = "http://192.168.10.45:8080/stream" // Cambia la IP según tu ESP32-CAM



    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                loadUrl(streamUrl)
            }
        }
    )
}

