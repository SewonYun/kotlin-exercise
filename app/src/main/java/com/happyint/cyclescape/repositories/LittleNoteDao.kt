package com.happyint.cyclescape.repositories

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.happyint.cyclescape.entities.LittleNote.data.DailyNoteData

@Dao
interface LittleNoteDao {

    @Query("SELECT * FROM dailynotedata WHERE day_data_id = :dayDataId")
    fun select(dayDataId: Int): DailyNoteData?

    @Upsert
    fun insert(dailyNoteData: DailyNoteData): Long

}