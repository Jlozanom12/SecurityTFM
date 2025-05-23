package com.firstapp.security.presentation.userImages

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.firstapp.security.presentation.userImages.resources.ImageData2
import kotlinx.coroutines.launch
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.firstapp.security.presentation.userImages.resources.StorageUsageBar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Screen que permite visualizar las imágenes tras recibirlas desde el servidor.
 * Muestra un indicador de carga y el almacenamiento usado.
 */
@Composable
fun ImageGalleryScreen() {
    // Estado para guardar las imágenes descargadas del servidor
    var images by remember { mutableStateOf<List<ImageData2>>(emptyList()) }
    // Estado para indicar si la descarga aún está en proceso
    var isLoading by remember { mutableStateOf(true) }
    // Estado para manejar mensajes de error en caso de fallo al obtener las imágenes
    var error by remember { mutableStateOf<String?>(null) }

    //Permite lanzar tareas asincrónicas desde la UI
    val coroutineScope = rememberCoroutineScope()
    //Valor de espacio de almacenamiento
    val storageUsed = 0.1f

    //Efecto lanzado para cargar imágenes
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Llamada a la función que obtiene las imágenes del servidor
                images = fetchImagesFromServer() as List<ImageData2>
            } catch (e: Exception) {
                error = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }


    when {
        isLoading -> {
            Box(Modifier.fillMaxSize() .background(Color(0xFF121C2B)), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Box(Modifier.fillMaxSize() .background(Color(0xFF121C2B))
                , contentAlignment = Alignment.Center) {
                Text(text = "Error: ${error}", style = MaterialTheme.typography.bodyMedium)
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF121C2B))
                    .padding(start = 8.dp, top = 40.dp)
            ) {
                item {
                    //Barra de uso de almacenamiento
                    StorageUsageBar(storageUsed)
                    //Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                }

                items(images) { imageData ->
                    ImageItem(imageData)
                }
            }
        }
    }
}

/**
 * Este componente toma una imagen en formato base64 la decodifica a un objeto `Bitmap`
 * la muestra dentro de una Card junto con su timestamp.
 */
@Composable
fun ImageItem(imageData: ImageData2) {
    // Decodifica la cadena de Base64 a bytes
    val decodedBytes = Base64.decode(imageData.image, Base64.DEFAULT)
    //Convierte los bytes en un objeto Bitmap
    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

    // Mostrar la imagen y el timestamp dentro de una Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            //Muestra la imagen decodificada
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Imagen recibida",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            //Muestra el timestamp si hay
            imageData.timestamp?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}/**
* Esta función realiza una solicitud HTTP GET a un servidor Flask que devuelve una lista de
* objetos JSON con imágenes codificadas en base64.Parte de la respuesta se parsea usando Moshi
* para convertir el JSON en una lista de objetos `ImageData2`
 */
suspend fun fetchImagesFromServer(): List<ImageData2> {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://50.17.211.163:5000/images")
            .build()

        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val jsonResponse = response.body?.string()
            Log.d("FETCH", "JSON recibido: $jsonResponse")

            // Moshi con soporte para Kotlin
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val type = Types.newParameterizedType(List::class.java, ImageData2::class.java)
            val adapter: JsonAdapter<List<ImageData2>> = moshi.adapter(type)

            val result = adapter.fromJson(jsonResponse!!) ?: emptyList()
            Log.d("FETCH", "Lista parseada: $result")

            result
        } else {
            throw Exception("Error al obtener las imágenes: ${response.code}")
        }
    }
}
/* Probar Screen
@Preview(showBackground = true)
@Composable
fun PreviewImageGallery() {
    ImageGalleryScreen()
}
 */

