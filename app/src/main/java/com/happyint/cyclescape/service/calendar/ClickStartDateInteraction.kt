package com.happyint.cyclescape.service.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.util.ViewModelProvider
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.LocalDate

@ExperimentalMaterial3Api
class ClickStartDateInteraction {

    private var _calendarViewModel: CalendarViewModel = ViewModelProvider.getCalendarViewModel()

    companion object {
        fun of(): ClickStartDateInteraction {
            return ClickStartDateInteraction()
        }
    }


    fun insertStartDate(localData: LocalDate) {

        _calendarViewModel.upsertDayData(
            DayData(
                id = 0,
                startDate = localData,
                endDate = null,
                hasLittleNote = false
            )
        )

    }


    fun insertEndDate(localData: LocalDate) {
        _calendarViewModel.updateEndDate(localData)
    }

    fun removeStartDate(dayData: DayData) {
        _calendarViewModel.removeData(dayData)
    }

}