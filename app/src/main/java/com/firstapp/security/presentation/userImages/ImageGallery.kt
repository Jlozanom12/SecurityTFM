package com.firstapp.security.presentation.userImages

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.firstapp.security.presentation.userImages.resources.ImageData2
import com.firstapp.security.presentation.userImages.resources.getImagesFromServer
import io.ktor.websocket.Frame
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import android.util.Base64
import android.util.Log
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
fun ImageGalleryScreen() {
    var images by remember { mutableStateOf<List<ImageData2>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
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
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${error}", style = MaterialTheme.typography.bodyMedium)
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                items(images) { imageData ->
                    ImageItem(imageData)
                }
            }
        }
    }
}

@Composable
fun ImageItem(imageData: ImageData2) {
    // Decodificar Base64
    val decodedBytes = Base64.decode(imageData.image, Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

    // Mostrar la imagen y el timestamp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Imagen recibida",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
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
}
suspend fun fetchImagesFromServer(): List<ImageData2> {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://44.211.127.233:5000/images")
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
@Preview(showBackground = true)
@Composable
fun PreviewImageGallery() {
    ImageGalleryScreen()
}


