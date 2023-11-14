package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.happyint.cyclescape.R
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.util.ViewModelProvider

@ExperimentalMaterial3Api
@Composable
fun DynamicElementList(
    closeCallback: () -> Unit
) {

    val calendarViewModel = ViewModelProvider.getCalendarViewModel()
    val dayData = calendarViewModel.uiState.collectAsState().value.selectedDayData

    startOrRemoveComponent(dayData, closeCallback)()

    TextButton(onClick = { closeCallback() }) {
        Text(text = stringResource(id = R.string.menu_note))
    }


}

@Composable
fun startOrRemoveComponent(dayData: DayData?, closeCallback: () -> Unit): @Composable () -> Unit {
    if (dayData == null) {
        return { StartDateButton(closeCallback) }
    }

    return { RemoveDateButton(closeCallback) }
}