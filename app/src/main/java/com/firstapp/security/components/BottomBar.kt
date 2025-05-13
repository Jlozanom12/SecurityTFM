package com.firstapp.security.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VideoCameraFront
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.firstapp.security.models.Routes


@Composable
fun BottomBar(navController: NavController){
    BottomAppBar(){
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){

            IconButton(onClick = { navController.navigate(Routes.StreamingScreen.routes) }) {
                Icon(Icons.Filled.VideoCameraFront, contentDescription = "VideoCamera")
            }
            IconButton(onClick = { navController.navigate(Routes.ProfileScreen.routes) }) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Person",
                )
            }
            IconButton(onClick = { navController.navigate(Routes.ImageGalleryScreen.routes) }) {
                Icon(
                    Icons.Filled.ImageSearch,
                    contentDescription = "ImageSearch",
                )

            }

        }
    }
}