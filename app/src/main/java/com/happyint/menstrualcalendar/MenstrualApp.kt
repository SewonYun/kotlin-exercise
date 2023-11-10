package com.happyint.menstrualcalendar

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieConstants
import com.happyint.menstrualcalendar.constants.UserPage
import com.happyint.menstrualcalendar.ui.calendar.LoadCalendar
import com.happyint.menstrualcalendar.ui.common.BottomNavBar
import com.happyint.menstrualcalendar.ui.common.TopBar
import com.happyint.menstrualcalendar.ui.home.LoadMainHome
import com.happyint.menstrualcalendar.ui.home.Opening
import com.happyint.menstrualcalendar.ui.notice.LoadNotice
import com.happyint.menstrualcalendar.ui.setting.LoadSettingMain
import kotlinx.coroutines.delay

@Composable
fun rememberAppState(context: Context = LocalContext.current) = remember(context) {
    AppState(context)
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MenstrualAppOf(
    appState: AppState = rememberAppState()
) {

    if (appState.isOnline) {

        val currentScreen = remember { mutableStateOf(UserPage.OPENING) }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(modifier = Modifier.height(56.dp)) {
                TopBar(currentScreen)
            }

            Box(modifier = Modifier.weight(1f)) {

                when (currentScreen.value) {
                    UserPage.OPENING -> Opening(LottieConstants.IterateForever)
                    UserPage.MAIN -> LoadMainHome()
                    UserPage.SETTING -> LoadSettingMain()
                    UserPage.NOTICE -> LoadNotice(listOf("test1", "test2"))
                    UserPage.CALENDAR -> LoadCalendar()
                }

                LaunchedEffect(Unit) {
                    delay(1000)
                    currentScreen.value = UserPage.MAIN
                }

            }

            Box(modifier = Modifier.height(56.dp)) {
                BottomNavBar(currentScreen)
            }

            currentScreen.value.GoBack(currentScreen = currentScreen)
        }

    } else {
        OfflineDialog { appState.refreshOnline() }
    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.connection_error_title)) },
        text = { Text(text = stringResource(R.string.connection_error_message)) },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(R.string.retry_label))
            }
        }
    )
}