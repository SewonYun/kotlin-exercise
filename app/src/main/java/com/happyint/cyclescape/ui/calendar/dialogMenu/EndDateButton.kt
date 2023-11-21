package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.R
import com.happyint.cyclescape.viewModels.CalendarViewModel

@Composable
fun EndDateButton(closeCallback: () -> Unit) {

    val calendarViewModel = viewModel<CalendarViewModel>()

    TextButton(onClick = {

        val date = calendarViewModel.uiState.value.selectedDate

        date?.let {
            calendarViewModel.insertStartDate(date)
        }

        closeCallback()
    }) {
        Text(text = stringResource(id = R.string.menu_end_date))
    }
}