package com.happyint.cyclescape.ui.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadNotice(noticeList: List<String>) {

    Column {

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {

            noticeList.forEachIndexed { _, content ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    Text(content, style = TextStyle(fontSize = 16.sp))
                }
            }
        }

    }

}

@Preview
@Composable
fun LoadNoticePreview() {
    LoadNotice(listOf("test", "test1", "test2", "test3"))
}
