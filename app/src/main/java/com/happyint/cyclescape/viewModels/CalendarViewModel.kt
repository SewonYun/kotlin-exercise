package com.happyint.cyclescape.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.getOrElse
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.entities.calendar.data.DayDataBuilder
import com.happyint.cyclescape.entities.calendar.vo.UIState
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import com.happyint.cyclescape.exception.NotFoundDataException
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.repositories.LittleNoteRepository
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
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val dayDataRepository: DayDataRepository,
    private val littleNoteRepository: LittleNoteRepository,
    private val unclosedEventChecker: UnclosedEventChecker,
    private val eventPeriodChecker: EventPeriodChecker
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    private var _monthPeriodData: MutableStateFlow<Map<String, DayData>> = MutableStateFlow(
        mutableMapOf()
    )
    val monthPeriodData: StateFlow<Map<String, DayData>> get() = _monthPeriodData.asStateFlow()

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

            return@withContext periodCheckResult.isNotEmpty()
        }

    }

    fun fetchMonthPeriodData() = viewModelScope.launch(Dispatchers.IO) {
        val dayDataList = dayDataRepository.dayData()
        val tmpMap = mutableMapOf<String, DayData>()

        for (dayData in dayDataList) {
            tmpMap[dayData.startDate.toString()] = dayData

            if (dayData.endDate == null) {
                continue
            }

            var nextDay = dayData.startDate.plusDays(1)

            while (nextDay <= dayData.endDate) {
                tmpMap[nextDay.toString()] = dayData
                nextDay = nextDay.plusDays(1)
            }
        }

        _monthPeriodData.value = tmpMap

    }

    fun upsertDayData(dayData: DayData) = viewModelScope.launch(Dispatchers.IO) {
        dayDataRepository.upsert(dayData)

        littleNoteRepository.bulkUpdateDayDataId(
            dayData.id,
            dayData.startDate,
            dayData.endDate
        )

        fetchMonthPeriodData().join()
    }

    fun resetOtherDays(dayData: DayData) = viewModelScope.launch(Dispatchers.IO) {
        dayDataRepository.upsert(dayData.copy(endDate = null))

        val dailyNoteData = littleNoteRepository.getByDate(dayData.startDate)

        littleNoteRepository.bulkUpdateDayDataId(
            null,
            dayData.startDate,
            dayData.endDate
        )

        updateDailyNoteData(dailyNoteData = dailyNoteData)

        fetchMonthPeriodData().join()
    }

    fun updateEndDate(date: LocalDate) = viewModelScope.launch(Dispatchers.IO) {

        val dayDataList = dayDataRepository.unclosedDayData(date)

        if (dayDataList.isEmpty()) {
            throw NotFoundDataException("Not found data by dayDataByDate.")
        }

        val dayData = dayDataList.last()

        upsertDayData(
            dayData.copy(
                endDate = date
            )
        )

        littleNoteRepository.bulkUpdateDayDataId(
            dayData.id,
            dayData.startDate,
            date
        )

        fetchMonthPeriodData().join()
    }

    fun removeData(dayData: DayData) = viewModelScope.launch(Dispatchers.IO) {
        dayDataRepository.delete(dayData)
        littleNoteRepository.bulkUpdateDayDataId(
            null,
            dayData.startDate,
            dayData.endDate
        )
        fetchMonthPeriodData().join()
    }

    fun insertStartDate(localData: LocalDate) {
        upsertDayData(
            DayDataBuilder.getEmptyDayData(localData)
        )

    }

    fun updateUIStateByDate(selectedDate: LocalDate) {
        val selectedDayData = _monthPeriodData.value[selectedDate.toString()]
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

    private fun updateDailyNoteData(dailyNoteData: DailyNoteData?) {
        dailyNoteData?.let {
            littleNoteRepository.upsert(it)
        }
    }

}