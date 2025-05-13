package com.firstapp.security.presentation.alerts.components

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun fetchAlertsFromServer(): List<AlertData> {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://50.17.211.163:5000/alerts")  // Cambia a la IP de tu EC2
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Error: ${response.code}")

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, AlertData::class.java)
        val adapter = moshi.adapter<List<AlertData>>(type)

        adapter.fromJson(response.body?.string() ?: "") ?: emptyList()
    }
}
