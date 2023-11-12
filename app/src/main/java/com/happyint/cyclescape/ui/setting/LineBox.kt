package com.happyint.cyclescape.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineBox(
    text: String,
    emoji: ImageVectorContainer,
    borderTop: Boolean = false,
    borderBottom: Boolean = false,
    cb: () -> Unit = {},
) {

    if (borderTop) {
        Divider(
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
    }

    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { cb() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, style = TextStyle(fontSize = 16.sp))

        if (emoji.emoji.isPresent) {
            Image(imageVector = emoji.emoji.get(), contentDescription = text)
        }
    }

    if (borderBottom) {
        Divider(
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}
