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
import com.happyint.cyclescape.customApi.conditional
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.entities.calendar.state.DayComponentState
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
        initial = (listOf())
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
            var date = 1
            var nextMonthStartDate = 1
            var prevDayData: DayData? = null

            for (z in 0..5) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    for (i in 0..6) {
                        if (date == 1 && i < firstDayOfWeek) {

                            val localDate = LocalDate.of(
                                daysInNextMonth.year,
                                daysInNextMonth.month,
                                daysInLastMonthLen - (6 - i)
                            )

                            if (prevPeriodDataMap[localDate.toString()] != null) {
                                prevDayData = prevPeriodDataMap[localDate.toString()]
                            }

                            Surface(modifier = Modifier.weight(1f)) {
                                DayGrid(
                                    daysInLastMonth, daysInLastMonthLen - firstDayOfWeek + i
                                            + 1, prevDayData, openDialog, isAlpha = true
                                )
                            }

                        } else if (date <= daysInMonth) {

                            val localDate = LocalDate.of(month.year, month.month, date)

                            if (periodDataMap[localDate.toString()] != null) {
                                prevDayData = periodDataMap[localDate.toString()]
                            }

                            Surface(modifier = Modifier.weight(1f)) {
                                DayGrid(month, date, prevDayData, openDialog, isAlpha = false)
                            }
                            date++
                        } else {

                            val localDate = LocalDate.of(
                                daysInNextMonth.year, daysInNextMonth.month,
                                nextMonthStartDate
                            )

                            if (nextPeriodDataMap[localDate.toString()] != null) {
                                prevDayData = nextPeriodDataMap[localDate.toString()]
                            }

                            Surface(modifier = Modifier.weight(1f)) {
                                DayGrid(
                                    daysInNextMonth, nextMonthStartDate, prevDayData,
                                    openDialog, isAlpha = true
                                )
                            }
                            nextMonthStartDate++
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun DayGrid(
    month: YearMonth, startDate: Int, dayData: DayData?, openDialog:
    MutableState<Boolean>, isAlpha: Boolean
) {
    if (dayData != null) {
        dayData.hasLittleNote = false
    }
    val color = when (month.atDay(startDate).dayOfWeek) {
        DayOfWeek.SUNDAY -> Color.Red
        DayOfWeek.SATURDAY -> Color.Blue
        else -> Color.Black
    }

    Box(
        modifier = Modifier
            .border(2.dp, md_theme_light_secondaryContainer)
            .fillMaxHeight()
            .fillMaxWidth()
            .conditional(isAlpha) {
                this.alpha(0.5f)
            }
    ) {

        val localDate = LocalDate.of(month.year, month.month, startDate)

        Day(
            localDate,
            color = color,
            dayComponentHandler(dayData, localDate)
        ) {
            openDialog.value = true
        }

    }
}

fun dayComponentHandler(dayData: DayData?, localDate: LocalDate): DayComponentState {

    if (dayData == null) {
        return DayComponentState(false, false, false, null)
    }

    val isStartDate = localDate == dayData.startDate
    val isMiddleDate =
        (dayData.endDate != null && dayData.endDate > localDate) && dayData.startDate < localDate
    val isEndDate = dayData.endDate != null && dayData.endDate == localDate

    return DayComponentState(
        isStartDate = isStartDate, isMiddleDate = isMiddleDate, isEndDate =
        isEndDate, dayData = dayData
    )
}
