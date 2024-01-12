package com.happyint.cyclescape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.ui.graphics.AppTheme
import com.happyint.cyclescape.viewModels.CalendarViewModel
import com.happyint.cyclescape.viewModels.ComposableCalendarViewModel
import com.happyint.cyclescape.viewModels.LittleNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            (Bootstrap()).on()
            AppTheme {
                val composableCalendarViewModel = viewModel<ComposableCalendarViewModel>()
                val calendarViewModel = viewModel<CalendarViewModel>()
                val littleNoteViewModel = viewModel<LittleNoteViewModel>()
                calendarViewModel.fetchMonthPeriodData()
                littleNoteViewModel.fetchLittleNote()
                composableCalendarViewModel.BootStrap()
                CycleScapeAppOf()
            }
        }
    }
}
