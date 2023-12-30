package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import java.time.LocalDate
import javax.inject.Inject

class LittleNoteRepository @Inject constructor(private val littleNoteDao: LittleNoteDao) {

    fun insert(dailyNoteData: DailyNoteData): Long {
        return littleNoteDao.insert(dailyNoteData = dailyNoteData)
    }

    fun getByDayDataId(noteDate: LocalDate): DailyNoteData? {
        return littleNoteDao.select(noteDate)
    }

}