package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.LittleNote.data.DailyNoteData
import javax.inject.Inject

class LittleNoteRepository @Inject constructor(private val littleNoteDao: LittleNoteDao) {

    fun insert(content: String) {
        littleNoteDao.insert(content = content)
    }

    fun getByUserId(id: Int): DailyNoteData? {
        return littleNoteDao.select(id = id)
    }

}