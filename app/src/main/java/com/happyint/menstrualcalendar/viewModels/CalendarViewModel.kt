package com.happyint.menstrualcalendar.viewModels

import androidx.lifecycle.ViewModel
import com.happyint.menstrualcalendar.entities.calendar.data.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class CalendarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    fun updateDate(newDate: LocalDate) {
        _uiState.value = _uiState.value.copy(selectedDate = newDate)
    }

    fun setLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }
}