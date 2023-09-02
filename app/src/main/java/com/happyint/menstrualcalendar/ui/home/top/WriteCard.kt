package com.happyint.menstrualcalendar.ui.home.top

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class WriteCard : CardSurfaceInterface {
    @Composable
    @Preview
    override fun DataPreview() {
        Column {
            Text(text = "롸이트", style = TextStyle(fontSize = 22.sp, color = Color.Black))
        }
    }
}
