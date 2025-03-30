package com.firstapp.security.models

sealed class Routes(val routes:String){
    object HomeScreen : Routes("HomeScreen")
    object CameraViewScreen: Routes("CameraViewScreen")
    object StreamingScreen: Routes("StreamingScreen")
    object  PreviewSecurityImagesScreen: Routes("PreviewSecurityImagesScreen")
}