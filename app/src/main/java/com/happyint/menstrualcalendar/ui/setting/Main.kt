package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.constants.UserPage

@ExperimentalMaterial3Api
@Composable
fun LoadSettingMain(currentScreen: MutableState<UserPage>) {


    Column {
        TopBar(currentScreen = currentScreen)

        Column(
            modifier = Modifier.verticalScroll(ScrollState(0))
        ) {
            ProfileImageBox(imagePath = R.drawable.rabbit_stamp)
            Spacer(modifier = Modifier.height(30.dp))

            UserInfo()

            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewLoadSettingMain() {
    val currentScreen = remember { mutableStateOf(UserPage.OPENING) }
    LoadSettingMain(currentScreen)
}