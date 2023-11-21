package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable

@Composable
fun OutSurface(content: @Composable @UiComposable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        content()
    }
}