package com.happyint.cyclescape.entities.calendar.data

import java.time.LocalDate

class DayDataBuilder {

    companion object {
        fun getEmptyDayData(startDate: LocalDate): DayData {
            return DayData(
                id = 0,
                startDate = startDate,
                endDate = null
            )
        }

    }
}