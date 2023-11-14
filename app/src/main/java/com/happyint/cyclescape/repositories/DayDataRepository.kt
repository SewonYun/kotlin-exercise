package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.YearMonth

class DayDataRepository(private val dayDataDao: DayDataDao) {

    fun dayData(): List<DayData> {
        return dayDataDao.getDayDataList()
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