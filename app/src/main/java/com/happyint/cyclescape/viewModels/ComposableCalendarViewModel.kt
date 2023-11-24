package com.happyint.cyclescape.viewModels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.happyint.cyclescape.viewModels.calendar.RememberState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.YearMonth
import javax.inject.Inject

@ExperimentalFoundationApi
class ComposableCalendarViewModel @Inject constructor() : ViewModel() {

    private lateinit var _months: MutableStateFlow<List<YearMonth>>
    private lateinit var _pagerState: MutableStateFlow<PagerState>

    val months: StateFlow<List<YearMonth>> get() = _months.asStateFlow()
    val pagerState: StateFlow<PagerState> get() = _pagerState.asStateFlow()

    fun updateMonth(months: List<YearMonth>) {
        _months.value = months
    }

    fun updatePagerState(pagerState: PagerState) {
        _pagerState.value = pagerState
    }

    @Composable
    fun BootStrap(): Unit {
        val stateContainer = RememberState().build().getCalendarRemember()
        _pagerState = MutableStateFlow(stateContainer.pagerState)
        _months = MutableStateFlow(stateContainer.months)
    }

}