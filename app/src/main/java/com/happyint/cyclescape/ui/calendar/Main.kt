package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.service.calendar.TopbarSyncDelegator
import com.happyint.cyclescape.viewModels.CalendarViewModel
import kotlinx.coroutines.flow.filter
import java.time.YearMonth


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun LoadCalendar() {

    Column {

        val currentMonth = YearMonth.now()
        var months by remember {
            mutableStateOf(List(3) {
                currentMonth.minusMonths(1).plusMonths(it.toLong())
            })
        }

        val pagerState = rememberPagerState(
            initialPage = 1,
            initialPageOffsetFraction = 0f
        ) {
            months.size
        }

        val cv = viewModel<CalendarViewModel>()
        cv.updateUIStateByCopy(cv.uiState.collectAsState().value.copy(month = months[pagerState.currentPage]))
        TopbarSyncDelegator.UiStateUpdate(cv, months, pagerState)

        LaunchedEffect(pagerState.currentPage) {

            cv.fetchMonthPeriodData(months[pagerState.currentPage])

            snapshotFlow { pagerState.isScrollInProgress }.filter { it == false }.collect {

                if (pagerState.currentPage <= 1) {  // 첫 번째 페이지에 도달했을 때

                    months = listOf(months.first().minusMonths(1)) + months
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else if (pagerState.currentPage >= months.size - 2) {  // 마지막 페이지에 도달했을 때

                    months = months + listOf(months.last().plusMonths(1))

                }

            }

        }

        CalendarPaddingView()

        VerticalPager(
            modifier = Modifier
                .weight(1f)
                .border(2.dp, Color.Gray),
            state = pagerState,
            beyondBoundsPageCount = 0,
            pageContent = {
                val month = months[it]

                OutSurface {

                    Column {
//                        CalendarHeader(month)
                        CalendarBody(month)
                    }

                }
            }
        )

    }

    val calendarViewModel = viewModel<CalendarViewModel>()
    DisposableEffect(Unit) {

        onDispose {
            val uiState = calendarViewModel.uiState.value
            calendarViewModel.updateUIStateByCopy(uiState.copy(month = null))
        }

    }

}


