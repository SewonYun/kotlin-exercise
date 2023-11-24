package com.happyint.cyclescape.viewModels.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import java.time.YearMonth

@ExperimentalFoundationApi
data class StateContainer(
    val months: List<YearMonth>,
    val pagerState: PagerState
)
