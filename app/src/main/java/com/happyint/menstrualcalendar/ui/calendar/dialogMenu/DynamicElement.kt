package com.happyint.menstrualcalendar.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.util.ViewModelProvider

@ExperimentalMaterial3Api
@Composable
fun DynamicElement(
    clickStartDateInteraction: ClickStartDateInteraction,
    closeCallback: () -> Unit
) {

    val calendarViewModel = ViewModelProvider.getCalendarViewModel()
    val dayData = calendarViewModel.uiState.collectAsState().value.selectedDayData

    TextButton(onClick = {
        clickStartDateInteraction.insertOrRemove()
        closeCallback()
    }) {
        val insertOrAskText = if (dayData == null) {
            stringResource(id = R.string.menu_start_date)
        } else {
            stringResource(id = R.string.ask_menu_date_remove)
        }
        Text(text = insertOrAskText)
    }

    if (dayData == null) {
        TextButton(onClick = { closeCallback() }) {
            Text(text = stringResource(id = R.string.menu_end_date))
        }
    }

    TextButton(onClick = { closeCallback() }) {
        Text(text = stringResource(id = R.string.menu_note))
    }

}