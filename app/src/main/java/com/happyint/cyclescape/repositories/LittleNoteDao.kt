package com.happyint.cyclescape.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import java.time.LocalDate

@Dao
interface LittleNoteDao {

    @Query("SELECT * FROM dailynotedata WHERE note_date = :noteDate")
    fun select(noteDate: LocalDate): DailyNoteData?

    @Query("SELECT * FROM dailynotedata ")
    fun all(): List<DailyNoteData>

    @Upsert
    fun upsert(dailyNoteData: DailyNoteData): Long

    @Delete
    fun delete(dailyNoteData: DailyNoteData)

}