package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.customApi.testBorder
import com.happyint.menstrualcalendar.util.ViewModelProvider
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun DisplayDaysOfMonth(month: YearMonth, openDialog: MutableState<Boolean>) {
    val daysInMonth = month.lengthOfMonth()
    val firstDayOfWeek = month.atDay(1).dayOfWeek.value % 7 // 일요일이 0이 되도록 조정
    val calendarViewModel = ViewModelProvider.getCalendarViewModel()

    val monthPeriodData = calendarViewModel.monthPeriodData.collectAsState(initial = (listOf()))
    val periodDataMap = monthPeriodData.value.associateBy { it.startDate.toString() }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testBorder()
    ) {
        Column {
            var startDate = 1
            while (startDate <= daysInMonth) {

                Row {

                    for (i in 0..6) {

                        if (startDate == 1 && i < firstDayOfWeek) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                            )
                        } else if (startDate <= daysInMonth) {

                            val color = when (month.atDay(startDate).dayOfWeek) {
                                DayOfWeek.SUNDAY -> Color.Red
                                DayOfWeek.SATURDAY -> Color.Blue
                                else -> Color.Black
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                val localDate = LocalDate.of(month.year, month.month, startDate)
                                Day(
                                    localDate,
                                    color = color,
                                    periodDataMap[localDate.toString()]
                                ) {
                                    openDialog.value = true
                                }

                            }
                            startDate++

                        } else {
                            // 날짜가 없는 경우 빈 박스를 표시합니다.
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
