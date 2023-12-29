package com.happyint.cyclescape.entities.LittleNote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyNoteData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "content") val content: String
)