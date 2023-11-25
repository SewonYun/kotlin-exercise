package com.happyint.cyclescape.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.LocalDate

@Dao
interface DayDataDao {

    @Query("SELECT * FROM day_data")
    fun getDayDataList(): List<DayData>

    @Query("SELECT * FROM day_data WHERE start_date = :date")
    fun getDayDataListByDate(date: LocalDate): List<DayData>

    @Query(
        "SELECT * FROM day_data WHERE start_date < :findDate AND end_date is null ORDER BY id " +
                "DESC"
    )
    fun getDayDataListUnclose(findDate: LocalDate): List<DayData>

    @Query(
        "SELECT * FROM day_data WHERE start_date < :findDate AND end_date >= :findDate ORDER BY " +
                "id DESC"
    )
    fun getDayDataListInEventPeriod(findDate: LocalDate): List<DayData>

    @Query(
        "SELECT * FROM day_data WHERE start_date >= :start AND start_date <= :end ORDER BY " +
                "id DESC"
    )
    fun getDayDataListFromBetween(start: LocalDate, end: LocalDate): List<DayData>

    @Query("SELECT * FROM day_data WHERE start_date >= :startDate and start_date <= :endDate")
    fun getDayDataListByMonth(startDate: LocalDate, endDate: LocalDate): List<DayData>

    @Upsert
    fun upsert(dayData: DayData)

    @Delete
    fun delete(dayData: DayData)
}