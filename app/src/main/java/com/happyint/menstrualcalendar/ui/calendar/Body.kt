package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.YearMonth

@Composable
fun CalendarBody(month: YearMonth) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = Color.Black,
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .defaultMinSize(minWidth = 300.dp, minHeight = 350.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column {
                DisplayWeekdaysRow()
                DisplayDaysOfMonth(month)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCalendarBody() {
    CalendarBody(month = YearMonth.now())
}