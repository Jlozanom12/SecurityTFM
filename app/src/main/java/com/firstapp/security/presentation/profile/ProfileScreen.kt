package com.firstapp.security.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.firstapp.security.R
import com.firstapp.security.models.Routes
import com.firstapp.security.presentation.profile.components.ProfileMenuItem

/**
 * Screen del Perfil del usuario
 */
@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121C2B))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileHeader()
        ProfileMenu(navController)
    }
}

/**
 * Parte superior del perfil con imagen de usuario
 */
@Composable
fun ProfileHeader() {
    Box(
        modifier = Modifier
            .padding(top = 50.dp, end = 20.dp)
            .size(120.dp)
            .clip(CircleShape)
            .clickable {}, // Aquí puedes agregar funcionalidad para cambiar la foto
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.anonimo),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
    }
}

/**
 *  Menú de opciones del perfil
 */
@Composable
fun ProfileMenu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))

        ProfileMenuItem("Información Personal", Icons.Default.Person, textColor = Color.White) {
             //navController.navigate(Routes.HomeScreen.routes)
        }
        ProfileMenuItem("Alertas", Icons.Default.AddAlert,textColor = Color.White) {
            navController.navigate(Routes.AlertScreen.routes)
        }

        ProfileMenuItem("Ayuda", Icons.Default.Info,textColor = Color.White) {
            navController.navigate(Routes.HelpScreen.routes)
        }

        Spacer(modifier = Modifier.height(100.dp))

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
        ProfileMenuItem("Cerrar Sesión", Icons.Default.Logout,textColor = Color.White) {
            //navController.navigate(Routes.HomeScreen.routes)
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}



