package com.happyint.cyclescape.service.calendar

import arrow.core.Option
import arrow.core.toOption
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.repositories.DayDataRepository
import java.time.LocalDate
import javax.inject.Inject


// never use singleton
class UnclosedEventChecker @Inject constructor(private val dayDataRepository: DayDataRepository) {

    fun findByDay(day: LocalDate): Option<List<DayData>> {
        return dayDataRepository.unclosedDayData(day).toOption()
    }

}