package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.happyint.cyclescape.R
import com.happyint.cyclescape.service.calendar.ClickStartDateInteraction
import com.happyint.cyclescape.util.ViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartDateButton(closeCallback: () -> Unit) {

    TextButton(onClick = {

        val service = ClickStartDateInteraction.of()
        val calendarViewModel = ViewModelProvider.getCalendarViewModel()

        service.insertStartDate(calendarViewModel.uiState.value.selectedDate!!)
        closeCallback()
    }) {
        Text(text = stringResource(id = R.string.menu_start_date))
    }
}