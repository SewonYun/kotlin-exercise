package com.happyint.cyclescape.service.calendar

import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.LocalDate
import javax.inject.Inject

class ClickStartDateInteraction @Inject constructor(val calendarViewModel: CalendarViewModel) {

    fun insertStartDate(localData: LocalDate) {

        calendarViewModel.upsertDayData(
            DayData(
                id = 0,
                startDate = localData,
                endDate = null,
                hasLittleNote = false
            )
        )

    }

    fun insertEndDate(localData: LocalDate) {
        calendarViewModel.updateEndDate(localData)
    }

    fun removeStartDate(dayData: DayData) {
        calendarViewModel.removeData(dayData)
    }

}