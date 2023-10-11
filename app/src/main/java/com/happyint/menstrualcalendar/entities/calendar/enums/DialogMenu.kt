package com.happyint.menstrualcalendar.entities.calendar.enums

import android.content.Context
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.util.ViewModelProvider

enum class DialogMenu(
    private val labelId: Int
) {

    PERIOD_START(R.string.menu_start_date) {
        override fun nextPhase() {
            val calendarViewModel = ViewModelProvider.getCalendarViewModel()
//            calendarViewModel.upsertDayData()
        }
    },
    PERIOD_END(R.string.menu_start_date) {
        override fun nextPhase() {
            TODO("Not yet implemented")
        }
    },
    LITTLE_NOTE(R.string.menu_start_date) {
        override fun nextPhase() {
            TODO("Not yet implemented")
        }
    };

    abstract fun nextPhase()
    fun getName(context: Context): String = context.getString(labelId)


}