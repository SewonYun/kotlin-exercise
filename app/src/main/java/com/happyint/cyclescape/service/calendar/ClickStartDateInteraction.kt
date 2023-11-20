package com.happyint.cyclescape.service.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.LocalDate
import javax.inject.Inject

@ExperimentalMaterial3Api
class ClickStartDateInteraction {

    @Inject
    lateinit var calendarViewModel: CalendarViewModel

    companion object {
        fun of(): ClickStartDateInteraction {
            return ClickStartDateInteraction()
        }
    }


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