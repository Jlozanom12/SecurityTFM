package com.firstapp.security.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.firstapp.security.presentation.cams.StreamingScreen
import com.firstapp.security.models.Routes
import com.firstapp.security.presentation.extras.CameraViewScreen
import com.firstapp.security.presentation.extras.HomeScreen
import com.firstapp.security.presentation.userImages.PreviewSecurityImagesScreen


@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.HomeScreen.routes) {

        composable(Routes.HomeScreen.routes) {
            HomeScreen()
        }
        composable(Routes.CameraViewScreen.routes) {
            CameraViewScreen()
        }
        composable(Routes.StreamingScreen.routes) {
            StreamingScreen(navController)
        }
        composable(Routes.PreviewSecurityImagesScreen.routes) {
            PreviewSecurityImagesScreen()
        }


    }
}