package com.firstapp.security.presentation.cams
/*
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource



@Composable
fun StreamingApp() {
    val context = LocalContext.current

    // URL del streaming de la ESP32-CAM
    val videoUrl = "http://192.168.14.92:8080/stream"

    // Configuración de ExoPlayer
    val player = ExoPlayer.Builder(context).build().apply {
        val dataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(videoUrl)))

        setMediaSource(mediaSource)
        prepare()
        playWhenReady = true
    }

    // Usamos StyledPlayerView de ExoPlayer para mostrar el video
    AndroidView(
        factory = {
            StyledPlayerView(context).apply {
                this.player = player
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StreamingApp()
}

 */