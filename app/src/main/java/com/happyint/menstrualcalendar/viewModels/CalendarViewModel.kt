package com.happyint.menstrualcalendar.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.menstrualcalendar.entities.calendar.data.DayData
import com.happyint.menstrualcalendar.repositories.DayDataRepository
import com.happyint.menstrualcalendar.ui.calendar.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalendarViewModel(private val dayDataRepository: DayDataRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    private var _totalPeriodData: MutableStateFlow<List<DayData>> = MutableStateFlow(listOf())
    val totalPeriodData: StateFlow<List<DayData>> get() = _totalPeriodData.asStateFlow()

    init {
        fetchTotalPeriodData()
    }

    private fun fetchTotalPeriodData() = viewModelScope.launch(Dispatchers.IO) {
        _totalPeriodData.value = dayDataRepository.dayData()
    }

    fun upsertDayData(dayData: DayData) = viewModelScope.launch(Dispatchers.IO) {
        dayDataRepository.upsert(dayData)
        fetchTotalPeriodData().join()
    }

    fun updateUIState(selectedDate: LocalDate) {
        val selectedDayData = _totalPeriodData.value.firstOrNull { it.startDate == selectedDate }
        _uiState.value = _uiState.value.copy(
            selectedDate = selectedDate,
            selectedDayData = selectedDayData
        )
    }

}