package com.happyint.menstrualcalendar.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.ui.home.middle.MiddleContent
import com.happyint.menstrualcalendar.ui.home.middle.MiddleTab
import com.happyint.menstrualcalendar.ui.home.top.TopBar
import com.happyint.menstrualcalendar.ui.home.top.TopSwipeContent

@Composable
@Preview
fun LoadMainHome() {

    Column {
        TopBar()

        Spacer(modifier = Modifier.height(16.dp))

        TopSwipeContent(cb = {})

        Spacer(modifier = Modifier.height(32.dp))

        val state = remember { mutableStateOf(0) }
        MiddleTab(state)
        Spacer(modifier = Modifier.height(10.dp))
        MiddleContent(state)
    }

}
