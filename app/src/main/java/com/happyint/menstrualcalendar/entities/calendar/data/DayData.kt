package com.happyint.menstrualcalendar.entities.calendar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * isStartData 와 is EndDayData 는 동시에 true 이거나 false 일 수 있다.
 */

@Entity(indices = [Index(value = ["date"])], tableName = "day_data")
data class DayData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val date: LocalDate,
    @ColumnInfo(name = "is_start") var isStartDayData: Boolean = false,
    @ColumnInfo(name = "is_end") var isEndDayData: Boolean = false,
    @ColumnInfo(name = "has_note") var hasLittleNote: Boolean = false
)
