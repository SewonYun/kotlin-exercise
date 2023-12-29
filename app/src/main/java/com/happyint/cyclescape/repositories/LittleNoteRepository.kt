package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.LittleNote.data.DailyNoteData
import javax.inject.Inject

class LittleNoteRepository @Inject constructor(private val littleNoteDao: LittleNoteDao) {

    fun insert(dailyNoteData: DailyNoteData): Long {
        return littleNoteDao.insert(dailyNoteData = dailyNoteData)
    }

    fun getByDayDataId(dayDataId: Int): DailyNoteData? {
        return littleNoteDao.select(dayDataId = dayDataId)
    }

}