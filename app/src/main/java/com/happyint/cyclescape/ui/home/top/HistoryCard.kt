package com.happyint.cyclescape.ui.home.top

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class HistoryCard : CardSurfaceInterface {
    @Composable
    override fun DataPreview() {
        Column {
            Text(text = "", style = TextStyle(fontSize = 22.sp, color = Color.Black))
        }
    }
}
