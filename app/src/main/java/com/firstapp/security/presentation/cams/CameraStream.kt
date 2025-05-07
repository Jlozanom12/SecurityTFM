package com.firstapp.security.presentation.cams


import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.firstapp.security.models.Routes
import com.firstapp.security.presentation.cams.components.CameraStreaming
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


/*
@Composable
fun StreamingScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar cámara")
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),

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
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                    ) {

                    CameraStreaming()
                }
            }
            CardNoCam()
        }
    }
}

 */

/* Streaming Screen buena
@Composable
fun StreamingScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar cámara")
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                modifier = Modifier
                    .weight(3f)
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                onClick = { navController.navigate(Routes.CameraViewScreen.routes) }
            ) {
                CameraStreaming()
            }
            Button(
                onClick = { navController.navigate(Routes.CameraViewScreen.routes) },
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp),
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                colors = ButtonColors(containerColor =  Color.White ,contentColor =  Color.White , disabledContentColor =  Color.White , disabledContainerColor =  Color.White  )
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Ver cámara",tint = Color.Black)
            }
        }
    }
}
*/
//Prueba  Capturas
@Composable
fun StreamingScreen(navController: NavController) {
    val webViewRef = remember { mutableStateOf<WebView?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    webViewRef.value?.let { webView ->
                        val bitmap = Bitmap.createBitmap(
                            webView.width,
                            webView.height,
                            Bitmap.Config.ARGB_8888
                        )
                        val canvas = Canvas(bitmap)
                        webView.draw(canvas)

                        // Llamada para subir la imagen
                        uploadSnapshot(bitmap, "http://50.17.211.163:5000/upload")
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Capturar imagen")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                modifier = Modifier
                    .weight(3f)
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                onClick = { navController.navigate(Routes.CameraViewScreen.routes) }
            ) {
                CameraStreaming(webViewRef)
            }

            Button(
                onClick = { navController.navigate(Routes.CameraViewScreen.routes) },
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp),
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Ver cámara",
                    tint = Color.Black
                )
            }
        }
    }
}

fun uploadSnapshot(bitmap: Bitmap, uploadUrl: String) {
    val stream = ByteArrayOutputStream()
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












/* Primera prueba cards
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
        CardNoCam()
    }
}

 */





