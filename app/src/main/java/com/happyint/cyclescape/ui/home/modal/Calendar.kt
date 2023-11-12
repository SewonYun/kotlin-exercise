@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.cyclescape.ui.home.modal

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection


@Preview
@Composable
fun PreviewDatePicker() {
    val calendarState = UseCaseState(visible = true)
    DatePicker(calendarState = calendarState)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(calendarState: UseCaseState) {

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date { date ->
            Log.d("selectedDate", "$date")
        }
    )

}