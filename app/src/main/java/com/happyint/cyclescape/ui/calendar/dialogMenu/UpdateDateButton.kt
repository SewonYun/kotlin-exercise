package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.R
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.viewModels.CalendarViewModel

@Composable
fun UpdateDateButton(dayData: DayData?, closeCallback: () -> Unit) {
    val calendarViewModel = viewModel<CalendarViewModel>()

    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {

            if (dayData != null) {
                calendarViewModel.upsertDayData(
                    dayData.copy(endDate = null)
                )
            }

            closeCallback()
        })
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.ask_menu_date_update))
        }
    }
}