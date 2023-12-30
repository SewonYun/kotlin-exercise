package com.happyint.cyclescape.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteDataBuilder
import com.happyint.cyclescape.repositories.LittleNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LittleNoteViewModel @Inject constructor(private val littleNoteRepository: LittleNoteRepository) :
    ViewModel() {

    private var _dailyNoteData: MutableStateFlow<DailyNoteData> =
        MutableStateFlow(DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate = null))

    val dailyNoteData: StateFlow<DailyNoteData> get() = _dailyNoteData.asStateFlow()

    fun fetchData(noteDate: LocalDate) = viewModelScope.launch(Dispatchers.IO) {
        _dailyNoteData.value =
            littleNoteRepository.getByDayDataId(noteDate)
                ?: DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate = noteDate)
    }

    suspend fun getDailyNoteDataByDayDataId(noteDate: LocalDate): DailyNoteData {
        fetchData(noteDate).join()
        return dailyNoteData.value
    }

    fun insert(dailyNoteData: DailyNoteData) = viewModelScope.launch(Dispatchers.IO) {
        littleNoteRepository.insert(dailyNoteData)
        fetchData(dailyNoteData.noteDate)
    }

}