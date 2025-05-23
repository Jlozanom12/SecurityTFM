package com.firstapp.security.presentation.extras

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.firstapp.security.R


// Screen de Ayuda con bases legales del proyecto
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HelpScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(modifier = Modifier.padding(start = 100.dp), text = "Ayuda e Información", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF121C2B),
                    titleContentColor = Color.White,
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF121C2B))
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                // LOGO DE LA EMPRESA
                Image(
                    painter = painterResource(id = R.drawable.betterimage_ai_1747324692773_removebg_preview), // Asegúrate que coincida el nombre
                    contentDescription = "Logo empresa",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "📖 ¿Cómo usar esta aplicación?",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "• Puedes ver la transmisión en vivo desde tu cámara en la sección principal.\n" +
                            "• Usa el botón flotante para capturar imágenes desde el streaming.\n" +
                            "• Accede a las imágenes almacenadas en la galería.\n" +
                            "• Consulta el historial de transmisiones y alertas.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "🛡️ Seguridad y privacidad",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Tu cámara está conectada a una red local o remota. Asegúrate de proteger tus credenciales de red. Las imágenes capturadas solo se almacenan si se detectan personas.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "⚖️ Derechos de autor y licencias",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Esta aplicación fue desarrollada con fines educativos.\n" +
                            "• Iconos proporcionados por Material Icons.\n" +
                            "• Algunas funciones utilizan el modelo YOLOv5 bajo licencia GPLv3.\n" +
                            "• Todo el contenido visual generado es propiedad del usuario.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(70.dp))

                Text(
                    text = "Versión 2.0.0 • © 2025 Security-Cam",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    )
}

