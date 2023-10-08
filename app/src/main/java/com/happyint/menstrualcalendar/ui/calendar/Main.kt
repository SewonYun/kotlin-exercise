package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.constants.UserPage
import com.happyint.menstrualcalendar.ui.setting.TopBar


@Composable
fun LoadCalendar(currentScreen: MutableState<UserPage>) {
    Column {
        TopBar(currentScreen = currentScreen)

        Column(
            modifier = Modifier.verticalScroll(ScrollState(0))
        ) {

            Spacer(modifier = Modifier.height(16.dp))
        }

    }

    Column {
        RabbitStamp()
    }
}