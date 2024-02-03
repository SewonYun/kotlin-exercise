package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.R
import com.happyint.cyclescape.service.push.PushNotificationManager.ShowPermissionRequest
import com.happyint.cyclescape.service.push.PushNotificationManager.notifyCycleReminder
import com.happyint.cyclescape.viewModels.CalendarViewModel

@Composable
fun EndDateButton(closeCallback: () -> Unit) {

    val calendarViewModel = viewModel<CalendarViewModel>()
    val isOpeningPermissionDialog = remember { mutableStateOf(false) }
    ShowPermissionRequest { isOpeningPermissionDialog }

    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {

            notifyCycleReminder { isOpeningPermissionDialog }

            if (isOpeningPermissionDialog.value) {
                return@TextButton
            }

            val date = calendarViewModel.uiState.value.selectedDate

            date?.let {
                calendarViewModel.updateEndDate(date)
            }

            closeCallback()
        })
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.menu_end_date), textAlign = TextAlign.Left)
        }
    }
}