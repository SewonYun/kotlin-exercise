package com.happyint.cyclescape.entities.calendar.vo

import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.LocalDate
import java.time.YearMonth

data class UIState(
    val selectedDate: LocalDate? = null,
    val selectedDayData: DayData? = null,
    val isLoading: Boolean = false,
    val month: YearMonth? = null
)

