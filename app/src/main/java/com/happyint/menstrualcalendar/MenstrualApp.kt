package com.happyint.menstrualcalendar

import android.content.Context
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieConstants
import com.happyint.menstrualcalendar.ui.home.LoadMainHome
import com.happyint.menstrualcalendar.ui.home.Opening
import com.happyint.menstrualcalendar.ui.home.modal.DatePicker
import kotlinx.coroutines.delay

@Composable
fun rememberAppState(
    context: Context = LocalContext.current
) = remember(context) {
    AppState(context)
}

@Composable
fun MenstrualAppOf(
    appState: AppState = rememberAppState()
) {
    if (appState.isOnline) {

        // 현재 화면 상태를 가지는 State 변수를 정의합니다.
        val currentScreen = remember { mutableStateOf(1) }

        // 현재 화면 상태에 따라 적절한 화면을 표시합니다.
        when (currentScreen.value) {
            1 -> Opening(LottieConstants.IterateForever)
            2 -> LoadMainHome()
        }

        LaunchedEffect(Unit) {
            // todo: When the data loading is complete, the lottie animation must be stopped.
            delay(5000)
            currentScreen.value = 2
        }

//        DatePicker("asdsda", "ddddd")
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