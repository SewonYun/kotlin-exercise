package com.happyint.cyclescape.entities.littleNote.vo

import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData

data class DayHasLittleNote(
    val dayData: DayData,
    val dailyNoteData: DailyNoteData
) {
    init {
        require(invalid(dayData, dailyNoteData)) {
            "DayData.id: ${dayData.id}, DailyNoteData.day_data_id: ${dailyNoteData.dayDataId}"
        }
    }

    private fun invalid(
        dayData: DayData,
        dailyNoteData: DailyNoteData
    ): Boolean {
        return dayData.id == dailyNoteData.dayDataId
    }
}
