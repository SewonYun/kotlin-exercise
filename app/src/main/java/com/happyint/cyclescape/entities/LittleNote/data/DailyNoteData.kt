package com.happyint.cyclescape.entities.LittleNote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    indices = [
        Index(value = ["day_data_id"], unique = true)
    ]
)
data class DailyNoteData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "day_data_id") val dayDataId: Int,
    @ColumnInfo(name = "content") val content: String
)