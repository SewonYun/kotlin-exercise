@file:OptIn(ExperimentalFoundationApi::class)

package com.happyint.cyclescape.viewModels.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.YearMonth

/**
 * Using in composeable function
 */
class RememberState {

    private lateinit var months: List<YearMonth>
    private lateinit var pagerState: PagerState
    private val currentMonth = YearMonth.now()

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun build(): RememberState {

        val rememberMonth by remember {
            mutableStateOf(List(20) {
                currentMonth.minusMonths(10).plusMonths(it.toLong())
            })
        }

        months = rememberMonth

        pagerState = rememberPagerState(
            initialPage = 10,
            initialPageOffsetFraction = 0f
        ) {
            Int.MAX_VALUE
        }

        return this
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun getCalendarRemember(): StateContainer {
        return StateContainer(months = months, pagerState = pagerState)
    }

}