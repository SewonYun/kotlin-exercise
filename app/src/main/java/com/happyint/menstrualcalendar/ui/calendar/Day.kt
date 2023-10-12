package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.happyint.menstrualcalendar.customApi.testBorder
import com.happyint.menstrualcalendar.entities.calendar.data.DayData
import com.happyint.menstrualcalendar.util.ViewModelProvider.getCalendarViewModel
import java.time.LocalDate


@Composable
fun Day(localDate: LocalDate, color: Color, dayData: DayData?, cb: () -> Unit) {
    val calendarViewModel = getCalendarViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                cb()
                calendarViewModel.updateUIState(localDate)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = localDate.dayOfMonth.toString(), color = color)

        if (dayData != null) {
            if (dayData.startDate == localDate) {
                Surface(
                    modifier = Modifier
                        .testBorder()
                        .background(color = Color.Red)
                ) {
                    Text(text = "Hoit")
                }
            }

            if (dayData.hasLittleNote) {
                Text(text = "블라블라", color = color)
            }

        }
    }
}