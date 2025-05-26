package com.firstapp.security.presentation.cams.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView




@Composable
fun CameraStreaming(webViewRef: MutableState<WebView?>) {
    val streamUrl = "http://192.168.10.37:8080/stream"


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

                //webViewRef.value = this // Guardamos referencia al WebView
            }
        }
    )
}






