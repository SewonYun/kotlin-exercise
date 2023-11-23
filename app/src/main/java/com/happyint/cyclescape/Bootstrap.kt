package com.happyint.cyclescape

import com.happyint.cyclescape.entities.user.data.Information
import com.happyint.cyclescape.entities.user.data.InformationBuilder
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.service.calendar.EventPeriodChecker
import com.happyint.cyclescape.service.calendar.UnclosedEventChecker
import com.happyint.cyclescape.viewModels.CalendarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.YearMonth

class Bootstrap {

    fun on() {

        CoroutineScope(Dispatchers.IO).launch {

            val db = CycleScapeApplication.instance.database
            val informationOption: Information? = (db.userDao()).select()
            if (informationOption == null) {
                (db.userDao()).insertInformation(InformationBuilder.getEmptyInformation())
            }
            val dayDataRepository = DayDataRepository(db.dayDataDao())
            val calendarViewModel = CalendarViewModel(
                dayDataRepository,
                UnclosedEventChecker(dayDataRepository),
                EventPeriodChecker(dayDataRepository)
            )

            calendarViewModel.fetchMonthPeriodData(YearMonth.now())

        }

    }
}