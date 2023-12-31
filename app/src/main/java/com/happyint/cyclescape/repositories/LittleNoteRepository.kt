package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import java.time.LocalDate
import javax.inject.Inject

class LittleNoteRepository @Inject constructor(private val littleNoteDao: LittleNoteDao) {

    fun upsert(dailyNoteData: DailyNoteData): Long {
        return littleNoteDao.upsert(dailyNoteData = dailyNoteData)
    }

    fun getByDate(noteDate: LocalDate): DailyNoteData? {
        return littleNoteDao.select(noteDate)
    }

    fun bulkUpdateDayDataId(dayDataId: Int?, startDate: LocalDate, endDate: LocalDate?) {

        val lastCondition = endDate ?: startDate
        var loopDate = startDate

        while (loopDate <= lastCondition) {
            val dailyNoteData = littleNoteDao.select(loopDate)

            if (dailyNoteData != null) {
                upsert(dailyNoteData.copy(dayDataId = dayDataId))
            }

            loopDate = loopDate.plusDays(1)
        }

    }

}