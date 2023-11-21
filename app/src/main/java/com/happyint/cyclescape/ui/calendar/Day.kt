package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.LocalDate


@Composable
fun Day(localDate: LocalDate, color: Color, dayData: DayData?, cb: () -> Unit) {
    val calendarViewModel = viewModel<CalendarViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                cb()
                calendarViewModel.updateUIState(localDate)
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

                if (dayData != null) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        if (dayData.startDate == localDate) {

                            Text(text = stringResource(id = R.string.range_start), fontSize = 10.sp)

                        }
                    }

                    Box {
                        if (dayData.hasLittleNote) {
                            Text(text = "블라블라", color = color)
                        }
                    }

                }
            }
        }


    }
}