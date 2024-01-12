package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.LocalDate
import javax.inject.Inject

class DayDataRepository @Inject constructor(private val dayDataDao: DayDataDao) {

    fun dayData(): List<DayData> {
        return dayDataDao.getDayDataList()
    }

    fun unclosedDayData(day: LocalDate): List<DayData> {
        return dayDataDao.getDayDataListUnclose(day)
    }

    fun eventPeriodDayData(day: LocalDate): List<DayData> {
        return dayDataDao.getDayDataListInEventPeriod(day)
    }

    fun eventPeriodDayData(start: LocalDate, end: LocalDate): List<DayData> {
        return dayDataDao.getDayDataListFromBetween(start, end)
    }


    fun upsert(dayData: DayData): Long {
        return dayDataDao.upsert(dayData)
    }

    fun delete(dayData: DayData) {
        dayDataDao.delete(dayData)
    }
}