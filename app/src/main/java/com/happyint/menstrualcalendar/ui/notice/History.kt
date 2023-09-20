package com.happyint.menstrualcalendar.ui.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happyint.menstrualcalendar.constants.UserPage
import com.happyint.menstrualcalendar.ui.setting.TopBar

@Composable
fun LoadNotice(currentScreen: MutableState<UserPage>, noticeList: List<String>) {

    Column {

        TopBar(currentScreen = currentScreen)

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            noticeList.forEachIndexed { index, content ->

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
    val currentScreen = remember { mutableStateOf(UserPage.NOTICE) }
    LoadNotice(currentScreen = currentScreen, listOf("test", "test1", "test2", "test3"))
}
