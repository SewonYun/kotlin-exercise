package com.happyint.menstrualcalendar.repositories

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.happyint.menstrualcalendar.entities.calendar.data.DayData

@Dao
interface DayDataDao {

    @Query("SELECT * FROM day_data")
    fun getDayDataList(): List<DayData>

    @Upsert
    fun upsert(dayData: DayData)
}