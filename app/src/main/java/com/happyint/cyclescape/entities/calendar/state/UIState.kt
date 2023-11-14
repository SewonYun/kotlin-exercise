package com.happyint.cyclescape.entities.calendar.state

import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.LocalDate

data class UIState(
    val selectedDate: LocalDate? = null,
    val selectedDayData: DayData? = null,
    val isLoading: Boolean = false
)

