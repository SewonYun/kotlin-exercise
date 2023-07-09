package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happyint.menstrualcalendar.R

@Composable
fun UserInfo() {
    Column {
        LineBox(stringResource(R.string.user_name), Icons.Default.Face)
        Divider(color = Color.Black, thickness = 1.dp)

        LineBox(stringResource(R.string.age), Icons.Default.Face)
        Divider(color = Color.Black, thickness = 1.dp)

        LineBox(stringResource(R.string.avg_cycle), Icons.Default.Face)
    }
}

@Composable
fun LineBox(text: String, emoji: ImageVector) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, style = TextStyle(fontSize = 16.sp))
        Image(imageVector = emoji, contentDescription = text)
    }
}

@Preview
@Composable
fun PreviewUserInfo() {
    UserInfo()
}