package com.happyint.menstrualcalendar.customApi

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

fun Modifier.testBorder(): Modifier {
    return then(Modifier.border(2.dp, Color.Gray))
}