package com.firstapp.security.presentation.cams


import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.firstapp.security.presentation.cams.components.CardComponent
import com.firstapp.security.presentation.cams.components.CardNoCam
import io.ktor.utils.io.errors.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.ByteArrayOutputStream


// Screen Principal de entrada de usuario en la que se muestran todas las cámaras disponibles
@Composable
fun StreamingScreen(navController: NavController) {
    val webViewRef = remember { mutableStateOf<WebView?>(null) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {webViewRef.value?.let { webView ->
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
                    uploadSnapshot(bitmap, "http://50.17.211.163:5000/upload2")
                }
                    Log.d("CameraViewScreen", "Floating Action Button presionado") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Cámara")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121C2B))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(modifier = Modifier.padding(top = 40.dp,start =20.dp),text ="Mis cámaras", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)

            CardComponent(navController)

            CardNoCam()
        }


    }
}

// Función encargada de subir la imagen(captura de pantalla del streaming) al servidor mediante una petición HTTP POST.
fun uploadSnapshot(bitmap: Bitmap, uploadUrl: String) {
    val stream = ByteArrayOutputStream()
    // Convierte el Bitmap en un array de bytes formato JPEG.
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val byteArray = stream.toByteArray()

    val requestBody = byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull())
    val request = Request.Builder()
        .url(uploadUrl)
        .post(requestBody)
        .build()

    OkHttpClient().newCall(request).enqueue(object : Callback {
         override fun onFailure(call: Call, e: IOException) {
            Log.e("UPLOAD", "Error al subir la imagen", e)
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                Log.d("UPLOAD", "Imagen subida correctamente")
            } else {
                Log.e("UPLOAD", "Error del servidor: ${response.code}")
            }
        }
    })
}






