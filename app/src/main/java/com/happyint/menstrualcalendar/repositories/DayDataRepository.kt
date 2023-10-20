package com.happyint.menstrualcalendar.repositories

import com.happyint.menstrualcalendar.entities.calendar.data.DayData

class DayDataRepository(private val dayDataDao: DayDataDao) {

    fun dayData(): List<DayData> {
        return dayDataDao.getDayDataList()
    }

    fun upsert(dayData: DayData) {
        dayDataDao.upsert(dayData)
    }
}