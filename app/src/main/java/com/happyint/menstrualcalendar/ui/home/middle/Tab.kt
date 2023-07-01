package com.happyint.menstrualcalendar.ui.home.middle

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun MiddleTab(tabIndex: MutableState<Int>) {
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    Column {
        TabRow(selectedTabIndex = 0) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex.value == index,
                    onClick = { tabIndex.value = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
    }
}

@Preview
@Composable
fun previewMiddleTab() {
    MiddleTab(remember { mutableStateOf(0) })
}