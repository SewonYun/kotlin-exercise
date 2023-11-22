package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.ui.graphics.md_theme_light_secondaryContainer
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun DisplayDaysOfMonth(month: YearMonth, openDialog: MutableState<Boolean>) {
    val daysInMonth = month.lengthOfMonth()
    val daysInLastMonth = month.minusMonths(1)
    val daysInLastMonthLen = daysInLastMonth.lengthOfMonth()
    val daysInNextMonth = month.plusMonths(1)

    val firstDayOfWeek = month.atDay(1).dayOfWeek.value % 7 // 일요일이 0이 되도록 조정
    val calendarViewModel = viewModel<CalendarViewModel>()

    val monthPeriodData = calendarViewModel.monthPeriodData.collectAsState(initial = (listOf()))
    val prevMonthPeriodData = calendarViewModel.prevMonthPeriodData.collectAsState(
        initial = (listOf
            ())
    )
    val nextMonthPeriodData =
        calendarViewModel.nextMonthPeriodData.collectAsState(initial = (listOf()))
    val periodDataMap = monthPeriodData.value.associateBy { it.startDate.toString() }
    val prevPeriodDataMap = prevMonthPeriodData.value.associateBy { it.startDate.toString() }
    val nextPeriodDataMap = nextMonthPeriodData.value.associateBy { it.startDate.toString() }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, md_theme_light_secondaryContainer)
    ) {
        Column {
            var startDate = 1
            var nextMonthStartDate = 1
            for (z in 0..5) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    for (i in 0..6) {
                        if (startDate == 1 && i < firstDayOfWeek) {
                            Box(
                                modifier = Modifier
                                    .border(2.dp, md_theme_light_secondaryContainer)
                                    .weight(1f)
                                    .fillMaxHeight() // 추가
                                    .alpha(0.5f)
                            ) {
                                val localDate = LocalDate.of(
                                    daysInLastMonth.year,
                                    daysInLastMonth.month,
                                    daysInLastMonthLen - firstDayOfWeek + i + 1
                                )
                                val color =
                                    when (daysInLastMonth.atDay(
                                        daysInLastMonthLen -
                                                firstDayOfWeek + i + 1
                                    ).dayOfWeek) {
                                        DayOfWeek.SUNDAY -> Color.Red
                                        DayOfWeek.SATURDAY -> Color.Blue
                                        else -> Color.Black
                                    }
                                Day(
                                    localDate,
                                    color = color,
                                    prevPeriodDataMap[localDate.toString()]
                                ) {}
                            }

                        } else if (startDate <= daysInMonth) {
                            val color = when (month.atDay(startDate).dayOfWeek) {
                                DayOfWeek.SUNDAY -> Color.Red
                                DayOfWeek.SATURDAY -> Color.Blue
                                else -> Color.Black
                            }
                            Box(
                                modifier = Modifier
                                    .border(2.dp, md_theme_light_secondaryContainer)
                                    .weight(1f)
                                    .fillMaxHeight() // 추가
                                    .fillMaxWidth() // 추가
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
                            Box(
                                modifier = Modifier
                                    .border(2.dp, md_theme_light_secondaryContainer)
                                    .weight(1f)
                                    .fillMaxHeight() // 추가
                                    .alpha(0.3f)

                            ) {
                                val localDate = LocalDate.of(
                                    daysInNextMonth.year, daysInNextMonth.month,
                                    nextMonthStartDate
                                )
                                val color =
                                    when (daysInNextMonth.atDay(nextMonthStartDate)
                                        .dayOfWeek) {
                                        DayOfWeek.SUNDAY -> Color.Red
                                        DayOfWeek.SATURDAY -> Color.Blue
                                        else -> Color.Black
                                    }
                                Day(
                                    localDate,
                                    color = color,
                                    nextPeriodDataMap[localDate.toString()]
                                ) {}
                                nextMonthStartDate++
                            }
                        }
                    }
                }
            }
        }
    }

}
