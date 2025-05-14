package com.firstapp.security

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firstapp.security.presentation.cams.StreamingScreen
import com.firstapp.security.components.BottomBar
import com.firstapp.security.models.Routes
import com.firstapp.security.presentation.alerts.AlertScreen
import com.firstapp.security.presentation.extras.CameraViewScreen
import com.firstapp.security.presentation.profile.ProfileScreen
import com.firstapp.security.presentation.extras.HelpScreen
import com.firstapp.security.presentation.userImages.ImageGalleryScreen
import com.firstapp.security.presentation.userImages.PreviewSecurityImagesScreen
import com.firstapp.security.ui.theme.SecurityTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecurityTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController) }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.StreamingScreen.routes
                    ) {
                        composable(Routes.StreamingScreen.routes) {
                            StreamingScreen(navController)
                        }
                        composable(Routes.CameraViewScreen.routes) {
                            CameraViewScreen()
                        }
                        composable(Routes.PreviewSecurityImagesScreen.routes) {
                            PreviewSecurityImagesScreen()
                        }
                        composable(Routes.ProfileScreen.routes) {
                            ProfileScreen(navController)
                        }
                        composable(Routes.ImageGalleryScreen.routes) {
                            ImageGalleryScreen()
                        }
                        composable(Routes.AlertScreen.routes) {
                            AlertScreen()
                        }
                        composable(Routes.HelpScreen.routes) {
                            HelpScreen()
                        }
                    }
                }
            }
        }
    }
}

