package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.R
import com.happyint.cyclescape.viewModels.CalendarViewModel

@Composable
fun StartDateButton(closeCallback: () -> Unit) {

    val calendarViewModel = viewModel<CalendarViewModel>()

    TextButton(onClick = {

        calendarViewModel.insertStartDate(calendarViewModel.uiState.value.selectedDate!!)
        closeCallback()
    }) {
        Text(text = stringResource(id = R.string.menu_start_date))
    }
}