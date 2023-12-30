package com.happyint.cyclescape.entities.littleNote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.happyint.cyclescape.entities.calendar.data.DayData
import java.time.LocalDate

@Entity(
    indices = [
        Index(value = ["note_date"], unique = true),
        Index(value = ["day_data_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = DayData::class,
            parentColumns = ["id"],
            childColumns = ["day_data_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DailyNoteData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "day_data_id") val dayDataId: Int,
    @ColumnInfo(name = "note_date") val noteDate: LocalDate,
    @ColumnInfo(name = "content") val content: String
)