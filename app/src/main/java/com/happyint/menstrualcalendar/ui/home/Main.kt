package com.happyint.menstrualcalendar.ui.home

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.PeriodApplication
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.ui.common.TopBar
import com.happyint.menstrualcalendar.ui.home.middle.MiddleContent
import com.happyint.menstrualcalendar.ui.home.middle.MiddleTab
import com.happyint.menstrualcalendar.ui.home.top.TopSwipeContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewLoadMainHome() {
    val a = rememberDrawerState(initialValue = DrawerValue.Closed)
    LoadMainHome(drawerState = a)
}

@ExperimentalMaterial3Api
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

    val openDialog = remember { mutableStateOf(false) }

    BackHandler {

        if (openDialog.value) {
            exitProcess(0)
        }

        openDialog.value = true

        Toast.makeText(
            PeriodApplication.instance,
            R.string.app_exit_dialog_asking,
            Toast.LENGTH_SHORT
        ).show()

        GlobalScope.launch(Dispatchers.IO) {
            delay(3000)
            openDialog.value = false
        }

    }

}
