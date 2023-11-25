package com.happyint.cyclescape.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.getOrElse
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.entities.calendar.state.UIState
import com.happyint.cyclescape.exception.NotFoundDataException
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.service.calendar.CalendarDialogPage
import com.happyint.cyclescape.service.calendar.EventPeriodChecker
import com.happyint.cyclescape.service.calendar.UnclosedEventChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val dayDataRepository: DayDataRepository,
    private val unclosedEventChecker: UnclosedEventChecker,
    private val eventPeriodChecker: EventPeriodChecker
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    private var _monthPeriodData: MutableStateFlow<List<DayData>> = MutableStateFlow(listOf())
    val monthPeriodData: StateFlow<List<DayData>> get() = _monthPeriodData.asStateFlow()
    private var _prevMonthPeriodData: MutableStateFlow<List<DayData>> = MutableStateFlow(listOf())
    val prevMonthPeriodData: StateFlow<List<DayData>> get() = _prevMonthPeriodData.asStateFlow()
    private var _nextMonthPeriodData: MutableStateFlow<List<DayData>> = MutableStateFlow(listOf())
    val nextMonthPeriodData: StateFlow<List<DayData>> get() = _nextMonthPeriodData.asStateFlow()

    suspend fun isInvalidation(clickDate: LocalDate): Boolean {

        return withContext(Dispatchers.IO) {

            val unclosedResult = unclosedEventChecker.findByDay(clickDate).getOrElse { listOf() }

            if (unclosedResult.isEmpty()) {
                return@withContext false
            }

            if (unclosedResult.size >= 2) {
                return@withContext true
            }

            val start = unclosedResult.first().startDate.plusDays(1)
            val periodCheckResult = eventPeriodChecker.findByDay(start, clickDate).getOrElse {
                listOf()
            }

            if (periodCheckResult.isNotEmpty()) {
                return@withContext true
            }

            return@withContext false
        }

    }

    fun fetchMonthPeriodData(month: YearMonth) = viewModelScope.launch(Dispatchers.IO) {
        _monthPeriodData.value = dayDataRepository.dayDataByMonth(month)
        _prevMonthPeriodData.value = dayDataRepository.dayDataByMonth(month.minusMonths(1))
        _nextMonthPeriodData.value = dayDataRepository.dayDataByMonth(month.plusMonths(1))
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

    fun insertStartDate(localData: LocalDate) {
        upsertDayData(
            DayData(
                id = 0,
                startDate = localData,
                endDate = null,
                hasLittleNote = false
            )
        )

    }

    fun updateUIStateByDate(selectedDate: LocalDate) {
        val selectedDayData = _monthPeriodData.value.firstOrNull { it.startDate == selectedDate }
        _uiState.value = _uiState.value.copy(
            selectedDate = selectedDate,
            selectedDayData = selectedDayData
        )
    }

    fun updateUIStateByCopy(uiState: UIState) {
        _uiState.value = uiState
    }

    fun initUIState() {
        _uiState.value = _uiState.value.copy(
            selectedDate = null,
            selectedDayData = null
        )
    }

    suspend fun dialogDependOn(date: LocalDate): CalendarDialogPage {

        return withContext(Dispatchers.IO) {
            val unclosedResult = unclosedEventChecker.findByDay(date).getOrElse { listOf() }
            val periodCheckResult = eventPeriodChecker.findByDay(date).getOrElse { listOf() }

            if ((unclosedResult.size + periodCheckResult.size) == 1) {
                updateUiStateInDialog(unclosedResult + periodCheckResult)
            }

            return@withContext when {
                periodCheckResult.isNotEmpty() -> CalendarDialogPage.UpdateDialog
                unclosedResult.isNotEmpty() -> CalendarDialogPage.EndDialog
                checkSelectDayEqualToDayData() -> CalendarDialogPage.CancelDialog
                else -> CalendarDialogPage.InsertDialog
            }

        }
    }

    private fun checkSelectDayEqualToDayData(): Boolean {
        return _uiState.value.selectedDayData != null
                && _uiState.value.selectedDayData!!.startDate == _uiState.value.selectedDate
    }

    private fun updateUiStateInDialog(result: List<DayData>) {

        val dayData = result.first()
        _uiState.value = _uiState.value.copy(
            selectedDayData = dayData
        )

    }

}