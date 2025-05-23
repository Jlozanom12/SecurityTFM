package com.firstapp.security.presentation.audio.components

import com.squareup.moshi.Json

// Clase de datos que representa la estructura utilizada en la base de datos
data class AudioData(
    @Json(name = "_id") val id: String,
    val dbfs: Double,
    val level: Int,
    val mic: Int,
    val timestamp: String,
    )
