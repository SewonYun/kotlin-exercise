package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.customApi.conditional
import com.happyint.cyclescape.entities.calendar.state.DayComponentState
import com.happyint.cyclescape.ui.graphics.md_theme_dark_secondary
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.LocalDate


@Composable
fun Day(localDate: LocalDate, color: Color, dayComponentState: DayComponentState, cb: () -> Unit) {
    val calendarViewModel = viewModel<CalendarViewModel>()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                cb()
                calendarViewModel.updateUIStateByDate(localDate)
            },
        contentAlignment = Alignment.Center
    ) {

        Column {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 12.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = localDate.dayOfMonth.toString(),
                    color = color
                )
            }

            Box(modifier = Modifier.weight(1f)) {

                if (!dayComponentState.isStartDate || !dayComponentState.isMiddleDate ||
                    !dayComponentState.isEndDate
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .conditional(
                                dayComponentState.isStartDate ||
                                        dayComponentState.isMiddleDate ||
                                        dayComponentState.isEndDate
                            ) {
                                this.background(color = md_theme_dark_secondary)
                            }
                    ) {
                        DayComponent(localDate)
                    }

                }
            }
        }

    }
}