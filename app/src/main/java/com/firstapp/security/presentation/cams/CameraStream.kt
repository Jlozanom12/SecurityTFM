package com.firstapp.security.presentation.cams


import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.firstapp.security.models.Routes
import com.firstapp.security.presentation.cams.components.CameraStreaming
import com.firstapp.security.presentation.cams.components.CardNoCam


/*
@Composable
fun StreamingScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar cámara")
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),

        ) {
            Spacer(modifier = Modifier.padding(20.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                onClick = { navController.navigate(Routes.CameraViewScreen.routes) }
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                    ) {

                    CameraStreaming()
                }
            }
            CardNoCam()
        }
    }
}

 */

@Composable
fun StreamingScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar cámara")
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                modifier = Modifier
                    .weight(3f)
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                onClick = { navController.navigate(Routes.CameraViewScreen.routes) }
            ) {
                CameraStreaming()
            }
            Button(
                onClick = { navController.navigate(Routes.CameraViewScreen.routes) },
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp),
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                colors = ButtonColors(containerColor =  Color.White ,contentColor =  Color.White , disabledContentColor =  Color.White , disabledContainerColor =  Color.White  )
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Ver cámara",tint = Color.Black)
            }
        }
    }
}













/* Primera prueba cards
@Composable
fun StreamingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(200.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),
            onClick = { navController.navigate(Routes.CameraViewScreen.routes) }
        ) {

        }
        CardNoCam()
    }
}

 */





