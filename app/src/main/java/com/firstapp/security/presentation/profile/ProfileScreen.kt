package com.firstapp.security.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.firstapp.security.R
import com.firstapp.security.models.Routes

/*
@Composable
fun ProfileScreen(navController: NavController){
ProfileContent(navController)
}
@Composable
fun ProfileContent(navController: NavController) {

    ProfileInfoContent2()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ClickableTextField(
            text = "Información Personal",
            onClick = { /*navController.navigate(Routes.ProfilePersonInfo.routes) */ },
            leadingIcon = Icons.Default.Person,
            textSize = 28.sp,

            )
        HorizontalDivider(modifier = Modifier.padding(4.dp))
        ClickableTextField(
            text = "Entradas",
            onClick = {/* navController.navigate(Routes.TicketScreen.routes) */ },
            leadingIcon = Icons.Default.List,
            textSize = 28.sp,

            )
        HorizontalDivider(modifier = Modifier.padding(4.dp))
        ClickableTextField(
            text = "Favoritos",
            onClick = { /*navController.navigate(Routes.FavoritesScreen.routes) */ },
            leadingIcon = Icons.Default.Favorite,
            textSize = 28.sp
        )
        HorizontalDivider(modifier = Modifier.padding(4.dp))
        ClickableTextField(
            text = "Ayuda",
            onClick = { /*navController.navigate(Routes.HelpScreen.routes)*/ },
            leadingIcon = Icons.Default.Info,
            textSize = 28.sp
        )

        HorizontalDivider(modifier = Modifier.padding(4.dp))
        Spacer(modifier = Modifier.padding(40.dp))
        ClickableTextField(
            modifier = Modifier.padding(18.dp),
            text = "Cerrar Sesión",
            onClick = { },
            leadingIcon = Icons.Default.Logout,
            textSize = 28.sp,
        )

        Spacer(modifier = Modifier.padding(100.dp))


    }
}
@Composable
fun ProfileInfoContent2() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .padding(start = 20.dp)
            .clickable {},

        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.anonimo),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
    }
}

 */

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeader()
        ProfileMenu(navController)
    }
}

/**
 * 📌 Sección de perfil con imagen de usuario
 */
@Composable
fun ProfileHeader() {
    Box(
        modifier = Modifier
            .padding(16.dp)
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
 * 📌 Menú de opciones del perfil
 */
@Composable
fun ProfileMenu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileMenuItem("Información Personal", Icons.Default.Person) {
             navController.navigate(Routes.HomeScreen.routes)
        }
        ProfileMenuItem("Entradas", Icons.Default.List) {
            navController.navigate(Routes.HomeScreen.routes)
        }
        ProfileMenuItem("Favoritos", Icons.Default.Favorite) {
            navController.navigate(Routes.HomeScreen.routes)
        }
        ProfileMenuItem("Ayuda", Icons.Default.Info) {
            navController.navigate(Routes.HomeScreen.routes)
        }

        Spacer(modifier = Modifier.height(40.dp))

        ProfileMenuItem("Cerrar Sesión", Icons.Default.Logout, isLogout = true) {
            navController.navigate(Routes.HomeScreen.routes)
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

/**
 * 📌 Item reutilizable del menú
 */
@Composable
fun ProfileMenuItem(text: String, icon: ImageVector, isLogout: Boolean = false, onClick: () -> Unit) {
    Column {
        ClickableTextField(
            text = text,
            onClick = onClick,
            leadingIcon = icon,
            textSize = 28.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isLogout) Color.Red else Color.Unspecified
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
    }
}

