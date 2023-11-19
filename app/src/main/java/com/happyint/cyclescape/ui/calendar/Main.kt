package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.happyint.cyclescape.customApi.testBorder
import com.happyint.cyclescape.util.ViewModelProvider
import kotlinx.coroutines.flow.filter
import java.time.YearMonth


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun LoadCalendar() {

    Column {

        val currentMonth = YearMonth.now()
        var months by remember {
            mutableStateOf(List(7) {
                currentMonth.minusMonths(3).plusMonths(it.toLong())
            })
        }

        val pagerState = rememberPagerState(
            initialPage = 3,
            initialPageOffsetFraction = 0f
        ) {
            months.size
        }

        val cv = ViewModelProvider.getCalendarViewModel()

        LaunchedEffect(pagerState.currentPage) {

            cv.fetchMonthPeriodData(months[pagerState.currentPage]).join()

            snapshotFlow { pagerState.isScrollInProgress }.filter { it == false }.collect {

                if (pagerState.currentPage <= 1) {  // 첫 번째 페이지에 도달했을 때

                    months = listOf(months.first().minusMonths(1)) + months
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else if (pagerState.currentPage >= months.size - 2) {  // 마지막 페이지에 도달했을 때

                    months = months + listOf(months.last().plusMonths(1))

                }

            }

        }

        VerticalPager(
            modifier = Modifier
                .weight(1f)
                .testBorder(),
            state = pagerState,
            beyondBoundsPageCount = 3,
            pageContent = {
                val month = months[it]

                OutSurface {

                    Column {
                        CalendarHeader(month)
                        CalendarBody(month)
                    }

                }
            }
        )

        CalendarBottomView()
    }

}

