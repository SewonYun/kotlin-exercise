package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happyint.menstrualcalendar.R

@Composable
fun UserInfo() {
    Column {

        LineBox(
            stringResource(R.string.birth_date),
            ImageVectorContainer(Icons.Default.Done),
            borderTop = true,
            borderBottom = true
        )

        WhiteSpace(60)

        LineBox(
            stringResource(R.string.avg_cycle),
            ImageVectorContainer(Icons.Default.AddCircle),
            borderTop = true,
            borderBottom = true
        )

        WhiteSpace(60)

        LineBox(
            stringResource(R.string.reminder),
            ImageVectorContainer(Icons.Default.AddCircle),
            borderTop = true,
            borderBottom = true
        )

        WhiteSpace(60)

        LineBox(
            stringResource(R.string.contact_us),
            ImageVectorContainer(Icons.Default.MailOutline),
            borderTop = true,
            borderBottom = true
        )
        WhiteSpace(60)
    }
}

@Composable
fun WhiteSpace(size: Int) {
    Divider(color = MaterialTheme.colorScheme.background, modifier = Modifier.height(size.dp))
}

@Composable
fun LineBox(
    text: String,
    emoji: ImageVectorContainer,
    borderTop: Boolean = false,
    borderBottom: Boolean = false
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, style = TextStyle(fontSize = 16.sp))

        if (!emoji.emoji.isEmpty) {
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

@Preview
@Composable
fun PreviewUserInfo() {
    UserInfo()
}