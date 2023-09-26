package com.happyint.menstrualcalendar

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieConstants
import com.happyint.menstrualcalendar.constants.UserPage
import com.happyint.menstrualcalendar.ui.common.LeftDrawerLayout
import com.happyint.menstrualcalendar.ui.home.LoadMainHome
import com.happyint.menstrualcalendar.ui.home.Opening
import com.happyint.menstrualcalendar.ui.notice.LoadNotice
import com.happyint.menstrualcalendar.ui.setting.LoadSettingMain
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(context: Context = LocalContext.current) = remember(context) {
    AppState(context)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MenstrualAppOf(
    appState: AppState = rememberAppState()
) {

    (Bootstrap()).on()

    if (appState.isOnline) {

        // 현재 화면 상태를 가지는 State 변수를 정의합니다.
        val currentScreen = remember { mutableStateOf(UserPage.OPENING) }

        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)

        scope.launch { drawerState.close() }
        // 현재 화면 상태에 따라 적절한 화면을 표시합니다.
        when (currentScreen.value) {
            UserPage.OPENING -> Opening(LottieConstants.IterateForever)
            UserPage.MAIN -> LeftDrawerLayout(
                drawerState,
                currentScreen
            ) { LoadMainHome(drawerState) }
            UserPage.SETTING -> LoadSettingMain(currentScreen)
            UserPage.NOTICE -> LoadNotice(currentScreen, listOf("test1", "test2"))
        }

        LaunchedEffect(Unit) {
            // todo: When the data loaded, it's lottie animation must stop.
            delay(1000)
            currentScreen.value = UserPage.MAIN
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