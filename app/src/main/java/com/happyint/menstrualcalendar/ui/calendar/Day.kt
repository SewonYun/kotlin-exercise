package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.happyint.menstrualcalendar.util.ViewModelProvider.getCalendarViewModel
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun Day(month: YearMonth, dayNumber: Int, color: Color, cb: () -> Unit) {
    val calendarViewModel = getCalendarViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                cb()
                calendarViewModel.updateUIDate(LocalDate.of(month.year, month.month, dayNumber))
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = dayNumber.toString(), color = color)
    }
}