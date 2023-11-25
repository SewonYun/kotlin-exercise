package com.happyint.cyclescape.service.calendar

import arrow.core.Option
import arrow.core.toOption
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.repositories.DayDataRepository
import java.time.LocalDate
import javax.inject.Inject

class EventPeriodChecker @Inject constructor(private val dayDataRepository: DayDataRepository) {

    fun findByDay(day: LocalDate): Option<List<DayData>> {
        val result = dayDataRepository.eventPeriodDayData(day)
        return result.toOption()
    }

    fun findByDay(start: LocalDate, click: LocalDate): Option<List<DayData>> {
        val result = dayDataRepository.eventPeriodDayData(start, click)
        return result.toOption()
    }

}