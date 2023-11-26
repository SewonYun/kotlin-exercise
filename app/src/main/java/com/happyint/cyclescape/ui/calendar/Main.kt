package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.service.calendar.TopbarSyncDelegator
import com.happyint.cyclescape.viewModels.CalendarViewModel
import com.happyint.cyclescape.viewModels.ComposableCalendarViewModel
import kotlinx.coroutines.flow.filter

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun LoadCalendar() {

    val composableCalendarViewModel = viewModel<ComposableCalendarViewModel>()
    var months = composableCalendarViewModel.months.collectAsState()
    val pagerState = composableCalendarViewModel.pagerState.collectAsState()
    val cv = viewModel<CalendarViewModel>()
    cv.updateUIStateByCopy(cv.uiState.collectAsState().value.copy(month = months.value[pagerState.value.currentPage]))
    TopbarSyncDelegator.UiStateUpdate(cv, months.value, pagerState.value)

    LaunchedEffect(pagerState.value.currentPage) {

        snapshotFlow { pagerState.value.isScrollInProgress }.filter { !it }.collect {
            if (pagerState.value.currentPage <= 1) {  // 첫 번째 페이지에 도달했을 때

                composableCalendarViewModel.updateMonth(
                    listOf(
                        months.value.first().minusMonths(1)
                    ) + months.value
                )
                pagerState.value.scrollToPage(pagerState.value.currentPage + 1)

            } else if (pagerState.value.currentPage >= months.value.size - 2) {  // 마지막 페이지에 도달했을 때

                composableCalendarViewModel.updateMonth(
                    months.value + listOf(
                        months.value.last().plusMonths(1)
                    )
                )

            }

        }

    }

    Column {

        CalendarPaddingView()
        DisplayWeekdaysRow()
        VerticalPager(
            modifier = Modifier
                .weight(1f)
                .border(2.dp, Color.Gray),
            state = pagerState.value,
            beyondBoundsPageCount = 0,
            pageContent = {
                val month = months.value[it]

                OutSurface {

                    Column {
                        CalendarBody(month)
                    }

                }
            }
        )

    }

    DisposableEffect(Unit) {

        onDispose {
            val uiState = cv.uiState.value
            cv.updateUIStateByCopy(uiState.copy(month = null))
        }

    }

}


