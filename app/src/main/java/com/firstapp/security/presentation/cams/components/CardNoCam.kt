package com.firstapp.security.presentation.cams.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstapp.security.R

/**
 * Componente para cámara no disponible (imágen como si no tuviera señal)
 */
@Composable
fun CardNoCam(){
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            Toast.makeText(context, "No hay cámara disponible", Toast.LENGTH_SHORT).show()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column( // Organiza la imagen arriba y el texto abajo
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.c_maratachada),
                    contentDescription = "Sin señal",
                    modifier = Modifier.size(150.dp) // Ajusta el tamaño de la imagen
                )
                Spacer(modifier = Modifier.height(8.dp)) // Espacio entre imagen y texto
                Text(
                    text = "NO SIGNAL",
                    fontSize = 20.sp,
                    color = Color.Red
                )
            }
        }
    }
}