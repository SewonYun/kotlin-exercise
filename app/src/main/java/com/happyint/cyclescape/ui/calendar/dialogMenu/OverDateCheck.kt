package com.happyint.cyclescape.ui.calendar.dialogMenu

import java.time.LocalDate

class OverDateCheck {

    companion object {
        fun isOver(date: LocalDate): Boolean {

            if (date > LocalDate.now()) {
                return true
            }

            return false
        }

    }

}