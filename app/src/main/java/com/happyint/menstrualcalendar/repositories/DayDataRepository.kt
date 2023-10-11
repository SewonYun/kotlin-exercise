package com.happyint.menstrualcalendar.repositories

import androidx.annotation.WorkerThread
import com.happyint.menstrualcalendar.entities.calendar.data.DayData

class DayDataRepository(private val dayDataDao: DayDataDao) {

    fun dayData(): List<DayData> {
        return dayDataDao.getDayDataList()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun upsert(dayData: DayData) {
        dayDataDao.upsert(dayData)
    }
}