package com.happyint.menstrualcalendar.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.ui.home.middle.MiddleContent
import com.happyint.menstrualcalendar.ui.home.middle.MiddleTab
import com.happyint.menstrualcalendar.ui.common.TopBar
import com.happyint.menstrualcalendar.ui.home.top.TopSwipeContent


@Preview
@Composable
fun PreviewLoadMainHome() {
    val a = rememberDrawerState(initialValue = DrawerValue.Closed)
    LoadMainHome(drawerState = a)
}

@Composable
fun LoadMainHome(drawerState: DrawerState) {

    Column(Modifier.verticalScroll(ScrollState(0))) {
        TopBar(drawerState)

        Spacer(modifier = Modifier.height(16.dp))

        TopSwipeContent(cb = {})

        Spacer(modifier = Modifier.height(32.dp))

        val state = remember { mutableStateOf(0) }
        MiddleTab(state)
        Spacer(modifier = Modifier.height(10.dp))
        MiddleContent(state)
    }

}
