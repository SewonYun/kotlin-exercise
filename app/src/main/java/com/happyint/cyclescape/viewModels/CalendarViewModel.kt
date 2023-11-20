package com.happyint.cyclescape.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.getOrElse
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.entities.calendar.state.UIState
import com.happyint.cyclescape.exception.NotFoundDataException
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.service.calendar.CalendarDialogPage
import com.happyint.cyclescape.service.calendar.UnclosedEventChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(private val dayDataRepository: DayDataRepository) :
    ViewModel
        () {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    private var _monthPeriodData: MutableStateFlow<List<DayData>> = MutableStateFlow(listOf())
    val monthPeriodData: StateFlow<List<DayData>> get() = _monthPeriodData.asStateFlow()

    fun fetchMonthPeriodData(month: YearMonth) = viewModelScope.launch(Dispatchers.IO) {
        _monthPeriodData.value = dayDataRepository.dayDataByMonth(month)
    }

    fun upsertDayData(dayData: DayData) = viewModelScope.launch(Dispatchers.IO) {
        dayDataRepository.upsert(dayData)
        fetchMonthPeriodData(
            month = YearMonth.of(
                dayData.startDate.year, dayData.startDate
                    .month
            )
        ).join()
    }

    fun updateEndDate(date: LocalDate) = viewModelScope.launch(Dispatchers.IO) {

        val dayDataList = dayDataRepository.unclosedDayData(date)

        if (dayDataList.isEmpty()) {
            throw NotFoundDataException("Not found data by dayDataByDate.")
        }

        val dayData = dayDataList.last()

        dayDataRepository.upsert(
            dayData.copy(
                endDate = date
            )
        )

        fetchMonthPeriodData(
            month = YearMonth.of(
                dayData.startDate.year, dayData.startDate
                    .month
            )
        ).join()
    }

    fun removeData(dayData: DayData) = viewModelScope.launch(Dispatchers.IO) {
        dayDataRepository.delete(dayData)
        fetchMonthPeriodData(
            month = YearMonth.of(
                dayData.startDate.year, dayData.startDate
                    .month
            )
        ).join()
    }

    fun updateUIState(selectedDate: LocalDate) {
        val selectedDayData = _monthPeriodData.value.firstOrNull { it.startDate == selectedDate }
        _uiState.value = _uiState.value.copy(
            selectedDate = selectedDate,
            selectedDayData = selectedDayData
        )
    }

    fun initUIState() {
        _uiState.value = _uiState.value.copy(
            selectedDate = null,
            selectedDayData = null
        )
    }

    fun dialogDependOn(date: LocalDate): CalendarDialogPage {

        val unclosedEventChecker = UnclosedEventChecker(dayDataRepository)
        val optionResult = unclosedEventChecker.findByDay(date)

        return when {
            _uiState.value.selectedDayData != null -> CalendarDialogPage.CancelDialog
            optionResult.getOrElse { listOf() }.isNotEmpty() -> CalendarDialogPage.EndDialog
            else -> CalendarDialogPage.InsertDialog
        }
    }

}