package com.firstapp.security.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *  Item reutilizable del menú
 */
@Composable
fun ProfileMenuItem(text: String, icon: ImageVector, textColor: Color, onClick: () -> Unit,) {
    Column {
        ClickableTextField(
            text = text,
            onClick = onClick,
            leadingIcon = icon,
            textSize = 28.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color =  textColor
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
    }
}