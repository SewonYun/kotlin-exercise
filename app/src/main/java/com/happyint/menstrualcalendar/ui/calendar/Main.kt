package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.constants.UserPage
import com.happyint.menstrualcalendar.ui.setting.TopBar
import java.time.YearMonth


@Composable
fun LoadCalendar(currentScreen: MutableState<UserPage>) {

    Column {
        TopBar(currentScreen = currentScreen)

        Column(
            modifier = Modifier.verticalScroll(ScrollState(0))
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            OutSurface {

                val currentMonth = YearMonth.of(2023, 1)
                val months = List(100) { currentMonth.plusMonths(it.toLong()) }

                val lazyListState = rememberLazyListState()
                val targetMonth = YearMonth.now()
                val targetIndex = months.indexOf(targetMonth)
                LaunchedEffect(Unit) {
                    lazyListState.animateScrollToItem(targetIndex)
                }

                LazyColumn(state = lazyListState) {
                    items(months) { month ->
                        Column {
                            CalendarHeader(month)
                            CalendarBody(month)
                        }
                    }
                }


            }
        }

    }

}