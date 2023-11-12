package com.happyint.cyclescape.ui.setting

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.cyclescape.R

@ExperimentalMaterial3Api
@Composable
fun LoadSettingMain() {


    Column {

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
    LoadSettingMain()
}