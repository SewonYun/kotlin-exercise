package com.happyint.cyclescape.entities.littleNote.data

import java.time.LocalDate

class DailyNoteDataBuilder {
    companion object {
        fun getEmptyDailyNoteData(noteDate: LocalDate?): DailyNoteData {
            return DailyNoteData(
                id = 0,
                dayDataId = 0,
                noteDate = noteDate ?: LocalDate.now(),
                content = ""
            )
        }

    }

}