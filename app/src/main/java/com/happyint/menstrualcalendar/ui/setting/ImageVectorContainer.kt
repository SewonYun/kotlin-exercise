package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Optional

data class ImageVectorContainer(val image: ImageVector? = null) {
    val emoji: Optional<ImageVector> = Optional.ofNullable(image)
}
