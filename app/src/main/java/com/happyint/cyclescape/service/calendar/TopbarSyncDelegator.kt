package com.happyint.cyclescape.service.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.happyint.cyclescape.R
import com.happyint.cyclescape.ui.graphics.agbalumoFamily
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.month_header, month.month.name, month.year),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp),
                    fontFamily = agbalumoFamily
                )
            }
        }

    }


}