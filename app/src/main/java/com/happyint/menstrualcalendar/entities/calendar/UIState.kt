package com.happyint.menstrualcalendar.entities.calendar

import java.time.LocalDate

data class UIState(val selectedDate: LocalDate? = null, val isLoading: Boolean = false)
