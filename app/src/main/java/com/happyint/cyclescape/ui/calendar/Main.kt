package com.happyint.cyclescape.ui.calendar

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.service.calendar.TopbarSyncDelegator
import com.happyint.cyclescape.viewModels.CalendarViewModel
import com.happyint.cyclescape.viewModels.ComposableCalendarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun LoadCalendar() {
    val openDialog = remember { mutableStateOf(false) }
    val composableCalendarViewModel = viewModel<ComposableCalendarViewModel>()
    val months = composableCalendarViewModel.months.collectAsState()
    val pagerState = composableCalendarViewModel.pagerState.collectAsState()
    val cv = viewModel<CalendarViewModel>()
    cv.updateUIStateByCopy(cv.uiState.collectAsState().value.copy(month = months.value[pagerState.value.currentPage]))
    TopbarSyncDelegator.UiStateUpdate(cv, months.value[pagerState.value.currentPage])

    if (pagerState.value.currentPage <= 7) {  // 첫 번째 페이지에 도달했을 때

        composableCalendarViewModel.updateMonth(
            List(10) {
                months.value.first().minusMonths(it.toLong() + 1)
            }.reversed() + months.value
        )

        CoroutineScope(Dispatchers.Main).launch {
            pagerState.value.scrollToPage(pagerState.value.currentPage + 9)
        }

    } else if (pagerState.value.currentPage >= months.value.size - 7) {  // 마지막 페이지에 도달했을 때

        composableCalendarViewModel.updateMonth(
            months.value + List(10) {
                months.value.last().plusMonths(it.toLong() + 1)
            }
        )

    }

    UserInputDialog(openDialog)

    Column {

        CalendarPaddingView()
        DisplayWeekdaysRow()

        val flingBehavior = PagerDefaults.flingBehavior(
            state = pagerState.value,
            pagerSnapDistance = PagerSnapDistance.atMost(1),
            lowVelocityAnimationSpec = tween(
                easing = FastOutLinearInEasing,
                durationMillis = 1
            ),
            highVelocityAnimationSpec = rememberSplineBasedDecay(),
            snapAnimationSpec = tween(
                easing = FastOutLinearInEasing,
                durationMillis = 1
            ),
        )

        VerticalPager(
            modifier = Modifier
                .weight(1f)
                .border(2.dp, Color.Gray),
            state = pagerState.value,
            flingBehavior = flingBehavior,
            beyondBoundsPageCount = 5,
            pageContent = {
                if (it < months.value.size) {
                    val month = months.value[it]

                    OutSurface {

                        Column {
                            CalendarBody(month, openDialog)
                        }

                    }
                } else {
                    Toast.makeText(
                        CycleScapeApplication.instance,
                        it.toString(),
                        Toast.LENGTH_SHORT
                    )
                        .show()
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


