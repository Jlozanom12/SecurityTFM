package com.firstapp.security.presentation.alerts.components

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

// Función suspendida
suspend fun fetchAlertsFromServer(): List<AlertData> {
    return withContext(Dispatchers.IO) {
        // Crea una nueva instancia del cliente HTTP de OkHttp.
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://50.17.211.163:5000/alerts")  // Cambia a la IP de tu EC2
            .build()

        // Ejecuta la petición HTTP de forma síncrona
        val response = client.newCall(request).execute()
        // Comprobar si la respuesta HTTP fue exitosa
        if (!response.isSuccessful) throw Exception("Error: ${response.code}")

        // Configuración de Moshi para Parsear JSON
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, AlertData::class.java)
        val adapter = moshi.adapter<List<AlertData>>(type)

        // Define el tipo de datos al que se va a parsear el JSON.
        // En este caso, una Lista de objetos AlertData.
        adapter.fromJson(response.body?.string() ?: "") ?: emptyList()
    }
}
