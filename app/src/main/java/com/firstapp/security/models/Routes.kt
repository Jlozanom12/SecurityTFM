package com.firstapp.security.models

sealed class Routes(val routes:String){
    object CameraViewScreen: Routes("CameraViewScreen")
    object StreamingScreen: Routes("StreamingScreen")
    object PreviewSecurityImagesScreen: Routes("PreviewSecurityImagesScreen")
    object ProfileScreen:Routes("ProfileScreen")
    object ImageGalleryScreen:Routes("ImageGalleryScreen")
    object AlertScreen:Routes("AlertScreen")
    object HelpScreen:Routes("HelpScreen")

}