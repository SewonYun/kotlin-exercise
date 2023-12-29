package com.happyint.cyclescape.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.happyint.cyclescape.entities.LittleNote.data.DailyNoteData

@Dao
interface LittleNoteDao {

    @Query("SELECT * FROM dailynotedata WHERE id = :id")
    fun select(id: Int): DailyNoteData?

    @Insert
    fun insert(content: String): Long

}