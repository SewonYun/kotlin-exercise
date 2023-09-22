package com.happyint.menstrualcalendar.entities.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Information(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String ?,
    @ColumnInfo(name = "birth") val birth: String ?
)
