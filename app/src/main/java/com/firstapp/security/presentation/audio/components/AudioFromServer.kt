package com.firstapp.security.presentation.audio.components

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

// Función suspendida que recupera los datos de audio de la nube
suspend fun fetchAudioEventsFromServer(): List<AudioData> {
    return withContext(Dispatchers.IO) {
        // Configuración del Cliente HTTP
        val client = OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS) // evita llamadas eternas
            .build()
        // Parte de manejo de errores por problemas en la recuperación de los datos
        val maxAttempts = 7
        var attempt = 0
        var lastError: Exception? = null

        while (attempt < maxAttempts) {
            try {
                Log.d("FETCH_AUDIO", "Intento ${attempt + 1} de $maxAttempts")

                val request = Request.Builder()
                    .url("http://50.17.211.163:5000/audio_events")
                    .build()
                // Ejecuta la petición
                val response = client.newCall(request).execute()

                if (!response.isSuccessful) {
                    throw IOException("Error HTTP: ${response.code}")
                }
                // / Obtiene el cuerpo de la respuesta como String y elimina los espacios
                val bodyString = response.body?.string()?.trim()
                Log.d("FETCH_AUDIO", "Cuerpo recibido: $bodyString")

                if (bodyString.isNullOrEmpty()) {
                    throw IOException("Respuesta vacía o nula")
                }
                // Parseo del JSON con Moshi
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                // Define lista de objetos tipo AudioData
                val type = Types.newParameterizedType(List::class.java, AudioData::class.java)
                val adapter = moshi.adapter<List<AudioData>>(type)

                val data = adapter.fromJson(bodyString)

                if (data != null) {
                    return@withContext data
                } else {
                    throw IOException("No se pudo parsear la respuesta")
                }

            } catch (e: Exception) {
                Log.e("FETCH_AUDIO", "Fallo en intento ${attempt + 1}: ${e.message}", e)
                lastError = e
                attempt++
                delay(1000L * attempt)
            }
        }

        throw lastError ?: IOException("Error desconocido al obtener audio events")
    }
}








