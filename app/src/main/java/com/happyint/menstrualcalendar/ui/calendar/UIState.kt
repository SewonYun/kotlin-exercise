package com.happyint.menstrualcalendar.ui.calendar

import com.happyint.menstrualcalendar.entities.calendar.data.DayData
import java.time.LocalDate

data class UIState(
    val selectedDate: LocalDate? = null,
    val selectedDayData: DayData? = null,
    val isLoading: Boolean = false,
)
