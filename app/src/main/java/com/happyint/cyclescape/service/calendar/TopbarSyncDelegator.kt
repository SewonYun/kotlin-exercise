package com.happyint.cyclescape.service.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.happyint.cyclescape.R
import com.happyint.cyclescape.viewModels.CalendarViewModel
import java.time.YearMonth

class TopbarSyncDelegator {

    companion object {
        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun UiStateUpdate(cv: CalendarViewModel, months: List<YearMonth>, pagerState: PagerState) {
            cv.updateUIStateByCopy(cv.uiState.collectAsState().value.copy(month = months[pagerState.currentPage]))
        }

        @Composable
        fun Grid(month: YearMonth) {
            Text(text = stringResource(id = R.string.month_header, month.month.name, month.year))
        }

    }


}