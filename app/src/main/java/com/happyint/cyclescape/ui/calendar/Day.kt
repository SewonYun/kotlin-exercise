package com.happyint.cyclescape.ui.calendar

import android.widget.Toast
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.entities.calendar.state.DayComponentState
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.LocalDate


@Composable
fun Day(localDate: LocalDate, color: Color, dayComponentState: DayComponentState, cb: () -> Unit) {
    val calendarViewModel = viewModel<CalendarViewModel>()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                Toast
                    .makeText(
                        CycleScapeApplication.instance, dayComponentState.dayData.toString(),
                        Toast.LENGTH_SHORT
                    )
                    .show()
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

                if (!dayComponentState.isStartDate || !dayComponentState.isMiddleDate ||
                    !dayComponentState.isEndDate
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        if (dayComponentState.isStartDate) {

                            Text(text = "!!!!!!!!!", fontSize = 10.sp)

                        }

                        if (dayComponentState.isMiddleDate) {

                            Text(text = "@@@@@@@@@@", fontSize = 10.sp)

                        }

                        if (dayComponentState.isEndDate) {

                            Text(text = "#############", fontSize = 10.sp)

                        }
                    }

                }
            }
        }

    }
}