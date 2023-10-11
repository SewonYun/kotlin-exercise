package com.happyint.menstrualcalendar.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.menstrualcalendar.entities.calendar.data.DayData
import com.happyint.menstrualcalendar.entities.calendar.data.UIState
import com.happyint.menstrualcalendar.repositories.DayDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalendarViewModel(private val dayDataRepository: DayDataRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    private var _totalPeriodData = MutableStateFlow(
        listOf(
            DayData(
                0, isStartDayData = false, isEndDayData = false, hasLittleNote = false,
                date = LocalDate.now()
            )
        )
    )
    val totalPeriodData: StateFlow<List<DayData>> get() = _totalPeriodData.asStateFlow()

    init {
        fetchTotalPeriodData()
    }

    private fun fetchTotalPeriodData() = viewModelScope.launch(Dispatchers.IO) {
        _totalPeriodData = MutableStateFlow(dayDataRepository.dayData())
    }

    fun upsertDayData(dayData: DayData) = viewModelScope.launch(Dispatchers.IO) {
        dayDataRepository.upsert(dayData)
        fetchTotalPeriodData()
    }

    fun updateUIDate(newDate: LocalDate) {
        _uiState.value = _uiState.value.copy(selectedDate = newDate)

    }

    fun setLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }
}