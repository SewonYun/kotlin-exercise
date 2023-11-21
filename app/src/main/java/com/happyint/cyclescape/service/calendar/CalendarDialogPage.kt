package com.happyint.cyclescape.service.calendar

sealed class CalendarDialogPage {
    object InsertDialog : CalendarDialogPage()
    object UpdateDialog : CalendarDialogPage()
    object CancelDialog : CalendarDialogPage()
    object EndDialog : CalendarDialogPage()

}
