package com.firstapp.security.presentation.cams.components

import android.webkit.WebView
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.firstapp.security.models.Routes


/**
 * Componente reutilizable que contiene las cards de las cámaras.
 */
@Composable
fun CardComponent(navController: NavController) {

    val webViewRef = remember { mutableStateOf<WebView?>(null) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(top = 220.dp))

        Card(
            shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
            modifier = Modifier
                .weight(3f)
                .height(200.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),
            onClick = { navController.navigate(Routes.CameraViewScreen.routes) }
        ) {
            CameraStreaming(/*webViewRef*/)
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