package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.R

@Composable
fun LoadSettingMain(currentScreen: MutableState<Int>) {

    Column {
        TopBar(currentScreen = currentScreen)

        ProfileImageBox(imagePath = R.drawable.card_sample1)
        Spacer(modifier = Modifier.height(30.dp))

        UserInfo()

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Preview
@Composable
fun PreviewLoadSettingMain() {
    val currentScreen = remember { mutableStateOf(1) }
    LoadSettingMain(currentScreen)
}