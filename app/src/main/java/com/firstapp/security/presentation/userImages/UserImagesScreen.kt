package com.firstapp.security.presentation.userImages


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstapp.security.R
import com.firstapp.security.presentation.userImages.resources.ImageData
import com.firstapp.security.presentation.userImages.resources.StorageUsageBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserImagesScreen(images: List<ImageData>) {
    val context = LocalContext.current
    var selectedImage by remember { mutableStateOf<ImageData?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Historial de Seguridad") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (images.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay imágenes disponibles", fontSize = 18.sp, color = Color.Gray)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(images.size) { index ->
                        val image = images[index]
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { selectedImage = image },
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = image.resourceId), // Para imágenes locales
                                    contentDescription = "Imagen de seguridad",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(120.dp)
                                )
                                Text(
                                    text = image.dateTime,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Mostrar imagen en grande al hacer clic
        if (selectedImage != null) {
            AlertDialog(
                onDismissRequest = { selectedImage = null },
                title = { Text("Imagen de Seguridad") },
                text = {
                    Image(
                        painter = painterResource(id = selectedImage!!.resourceId),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )
                },
                confirmButton = {
                    Button(onClick = { selectedImage = null }) {
                        Text("Cerrar")
                    }
                }
            )
        }
    }
}
// Simulación de la Screen con la Progress Bar
@Composable
fun SecurityImagesScreenWithStorage(images: List<ImageData>, storageUsed: Float) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Muestra las imágenes en la parte superior
            UserImagesScreen(images)
        }

        // Coloca la Progress Bar fija en la parte inferior
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 106.dp)
        ) {
            StorageUsageBar(storageUsed)
        }
    }
}


// Ejemplo de uso con imágenes de drawable
@Composable
fun PreviewSecurityImagesScreen() {
    SecurityImagesScreenWithStorage(
        images = listOf(
            ImageData(R.drawable.person_detected_20250218_092909, "2025-02-18 9:29"),
            ImageData(R.drawable.person_detected_20250218_132634, "2025-02-18 13:26"),
            ImageData(R.drawable.person_detected_20250218_133642, "2025-02-18 13:36")
        ),
        storageUsed = 0.10f
    )
}
