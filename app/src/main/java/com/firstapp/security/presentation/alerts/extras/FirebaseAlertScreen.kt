package com.firstapp.security.presentation.alerts.extras

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.firstapp.security.R

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Nuevo token: $token")

        // Opcional: envíalo a tu servidor para guardarlo
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            showNotification(it.title ?: "Alerta", it.body ?: "Movimiento detectado")
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(this, "alert_channel")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager = NotificationManagerCompat.from(this)
        manager.notify(1, builder.build())
    }
}
