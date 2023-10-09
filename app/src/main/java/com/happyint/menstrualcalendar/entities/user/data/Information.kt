package com.happyint.menstrualcalendar.entities.user.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Information(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "birth") val birth: String?,
    @ColumnInfo(name = "average_menstrual_cycle") val averageMenstrualCycle: Int
)
