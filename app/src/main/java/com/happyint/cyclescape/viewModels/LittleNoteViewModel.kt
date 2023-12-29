package com.happyint.cyclescape.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.cyclescape.entities.LittleNote.data.DailyNoteData
import com.happyint.cyclescape.entities.LittleNote.data.DailyNoteDataBuilder
import com.happyint.cyclescape.repositories.LittleNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LittleNoteViewModel @Inject constructor(private val littleNoteRepository: LittleNoteRepository) :
    ViewModel() {

    private var _dailyNoteData: MutableStateFlow<DailyNoteData> =
        MutableStateFlow(DailyNoteDataBuilder.getEmptyDailyNoteData())

    val dailyNoteData: StateFlow<DailyNoteData> get() = _dailyNoteData.asStateFlow()

    fun fetchData(dayDataId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _dailyNoteData.value =
            littleNoteRepository.getByDayDataId(dayDataId)
                ?: DailyNoteDataBuilder.getEmptyDailyNoteData()
    }

    suspend fun getDailyNoteData(dayDataId: Int): DailyNoteData {
        fetchData(dayDataId).join()
        return dailyNoteData.value
    }

    fun insert(dailyNoteData: DailyNoteData) = viewModelScope.launch(Dispatchers.IO) {
        littleNoteRepository.insert(dailyNoteData)
    }

}