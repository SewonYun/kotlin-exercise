package com.happyint.cyclescape.entities.calendar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * isStartData 와 is EndDayData 는 동시에 true 이거나 false 일 수 있다.
 */

@Entity(
    indices = [
        Index(value = ["start_date", "end_date"], unique = true)
    ], tableName = "day_data"
)
data class DayData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "start_date") val startDate: LocalDate,
    @ColumnInfo(name = "end_date") val endDate: LocalDate,
    @ColumnInfo(name = "has_note") var hasLittleNote: Boolean = false
)
