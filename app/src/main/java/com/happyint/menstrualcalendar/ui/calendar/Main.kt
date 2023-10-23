package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.constants.UserPage
import com.happyint.menstrualcalendar.ui.setting.TopBar
import java.time.YearMonth


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun LoadCalendar(currentScreen: MutableState<UserPage>) {

    Column {
        TopBar(currentScreen = currentScreen)

        val currentMonth = YearMonth.now()
        val months = List(5) { currentMonth.minusMonths(4).plusMonths(it.toLong()) }
        val pagerState = rememberPagerState(initialPage = months.size / 2)

        VerticalPager(state = pagerState, modifier = Modifier, pageCount = months.size) { page ->
            val month = months[page]


            Spacer(modifier = Modifier.height(120.dp))

            OutSurface {

                Column {
                    CalendarHeader(month)
                    CalendarBody(month)
                }
            }


        }


    }

}


