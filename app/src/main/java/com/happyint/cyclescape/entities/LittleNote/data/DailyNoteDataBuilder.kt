package com.happyint.cyclescape.entities.LittleNote.data

class DailyNoteDataBuilder {
    companion object {
        fun getEmptyDailyNoteData(): DailyNoteData {
            return DailyNoteData(id = 0, dayDataId = 0, content = "")
        }

    }

}