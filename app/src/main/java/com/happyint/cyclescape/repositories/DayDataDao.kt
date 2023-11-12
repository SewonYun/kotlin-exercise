package com.happyint.cyclescape.repositories

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.LocalDate

@Dao
interface DayDataDao {

    @Query("SELECT * FROM day_data")
    fun getDayDataList(): List<DayData>

    @Query("SELECT * FROM day_data WHERE start_date >= :startDate and start_date <= :endDate")
    fun getDayDataListByMonth(startDate: LocalDate, endDate: LocalDate): List<DayData>

    @Upsert
    fun upsert(dayData: DayData)
}