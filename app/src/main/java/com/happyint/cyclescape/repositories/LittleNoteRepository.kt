package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import java.time.LocalDate
import javax.inject.Inject

class LittleNoteRepository @Inject constructor(
    private val littleNoteDao: LittleNoteDao
) {

    fun getAll(): List<DailyNoteData> {
        return littleNoteDao.all()
    }

    fun upsert(dailyNoteData: DailyNoteData): Long {
        return littleNoteDao.upsert(dailyNoteData = dailyNoteData)
    }

    fun delete(dailyNoteData: DailyNoteData) {
        littleNoteDao.delete(dailyNoteData = dailyNoteData)
    }

    fun getByDate(noteDate: LocalDate): DailyNoteData? {
        return littleNoteDao.select(noteDate)
    }

}