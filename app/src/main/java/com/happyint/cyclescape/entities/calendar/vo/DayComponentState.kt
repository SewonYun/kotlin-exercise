package com.happyint.cyclescape.entities.calendar.vo

import com.happyint.cyclescape.entities.calendar.data.DayData

data class DayComponentState(
    val isStartDate: Boolean,
    val isMiddleDate: Boolean,
    val isEndDate: Boolean,
    val dayData: DayData?
)
