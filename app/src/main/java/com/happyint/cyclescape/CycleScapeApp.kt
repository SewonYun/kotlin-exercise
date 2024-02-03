package com.happyint.cyclescape

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieConstants
import com.happyint.cyclescape.constants.UserPage
import com.happyint.cyclescape.service.push.PushNotificationManager
import com.happyint.cyclescape.ui.calendar.LoadCalendar
import com.happyint.cyclescape.ui.common.BottomNavBar
import com.happyint.cyclescape.ui.common.TopBar
import com.happyint.cyclescape.ui.home.LoadMainHome
import com.happyint.cyclescape.ui.home.Opening
import com.happyint.cyclescape.ui.notice.LoadNotice
import com.happyint.cyclescape.ui.setting.LoadSettingMain
import com.happyint.cyclescape.viewModels.CalendarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CycleScapeAppOf() {

    val currentPage = remember { mutableStateOf(UserPage.MAIN) }
    val calendarViewModel = viewModel<CalendarViewModel>()

    val isOpening = remember { mutableStateOf(true) }

    if (isOpening.value) {
        Opening(LottieConstants.IterateForever)
    }

    val isOpeningPermissionDialog = remember { mutableStateOf(false) }
    PushNotificationManager.permissionRequire { isOpeningPermissionDialog }
    PushNotificationManager.ShowPermissionRequest { isOpeningPermissionDialog }

    CoroutineScope(Dispatchers.IO).launch {

        calendarViewModel.fetchMonthPeriodData().join()
        delay(1000)
        isOpening.value = false

    }

    if (isOpening.value) {
        return
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(modifier = Modifier.height(56.dp)) {
            TopBar()
        }

        Box(modifier = Modifier.weight(1f)) {

            when (currentPage.value) {
                UserPage.MAIN -> LoadMainHome()
                UserPage.SETTING -> LoadSettingMain()
                UserPage.CALENDAR -> LoadCalendar()
                UserPage.NOTICE -> LoadNotice(listOf("test1", "test2"))
            }

        }

        Box(modifier = Modifier.height(56.dp)) {
            BottomNavBar(currentPage)
        }

        val openDialog = remember { mutableStateOf(false) }

        BackHandler {

            if (openDialog.value) {
                exitProcess(0)
            }

            openDialog.value = true

            Toast.makeText(
                CycleScapeApplication.instance,
                R.string.app_exit_dialog_asking,
                Toast.LENGTH_SHORT
            ).show()

            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                openDialog.value = false
            }

        }
    }

}