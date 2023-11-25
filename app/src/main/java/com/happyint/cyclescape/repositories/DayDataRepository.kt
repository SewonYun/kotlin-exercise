package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

class DayDataRepository @Inject constructor(private val dayDataDao: DayDataDao) {

    fun dayData(): List<DayData> {
        return dayDataDao.getDayDataList()
    }

    fun dayDataByDate(date: LocalDate): List<DayData> {
        return dayDataDao.getDayDataListByDate(date)
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

    // test require first of month and end of month
    fun dayDataByMonth(month: YearMonth): List<DayData> {
        return dayDataDao.getDayDataListByMonth(month.atDay(1), month.atEndOfMonth())
    }

    fun upsert(dayData: DayData) {
        dayDataDao.upsert(dayData)
    }

    fun delete(dayData: DayData) {
        dayDataDao.delete(dayData)
    }
}