package com.happyint.menstrualcalendar.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import com.happyint.menstrualcalendar.entities.calendar.data.DayData
import com.happyint.menstrualcalendar.util.ViewModelProvider
import com.happyint.menstrualcalendar.viewModels.CalendarViewModel
import java.time.LocalDate

@ExperimentalMaterial3Api
class ClickStartDateInteraction(val show: () -> Unit, val close: () -> Unit) {

    private var _calendarViewModel: CalendarViewModel? = null

    init {
        _calendarViewModel = ViewModelProvider.getCalendarViewModel()
    }

    fun insertOrRemove() {

        val selectedDayData = _calendarViewModel!!.uiState.value.selectedDayData
        val localData: LocalDate = _calendarViewModel!!.uiState.value.selectedDate!!

        if (selectedDayData == null) {
            insertStartDate(localData)
        } else {
            show()
        }

    }

    private fun insertStartDate(localData: LocalDate) {
        _calendarViewModel!!.upsertDayData(
            DayData(
                id = 0,
                startDate = localData,
                endDate = localData,
                hasLittleNote = false
            )
        )
    }

}