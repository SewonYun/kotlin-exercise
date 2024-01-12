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

    val littleNoteData: StateFlow<MutableMap<String, DailyNoteData>>
        get() = _littleNoteData
            .asStateFlow()

    private var _littleNoteData: MutableStateFlow<MutableMap<String, DailyNoteData>> =
        MutableStateFlow(mutableMapOf())

    fun fetchLittleNote() = viewModelScope.launch(Dispatchers.IO) {
        val tmpMap = mutableMapOf<String, DailyNoteData>()
        val littleNoteList = littleNoteRepository.getAll()

        for (littleNote in littleNoteList) {
            tmpMap[littleNote.noteDate.toString()] = littleNote
        }

        _littleNoteData.value = tmpMap
    }

    private var _dailyNoteData: MutableStateFlow<DailyNoteData> =
        MutableStateFlow(DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate = null))

    val dailyNoteData: StateFlow<DailyNoteData> get() = _dailyNoteData.asStateFlow()


    fun fetchData(noteDate: LocalDate) = viewModelScope.launch(Dispatchers.IO) {
        _dailyNoteData.value =
            littleNoteRepository.getByDate(noteDate)
                ?: DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate = noteDate)
    }

    suspend fun getDailyNoteDataByDayDataId(noteDate: LocalDate): DailyNoteData {
        fetchData(noteDate).join()
        fetchLittleNote().join()
        return dailyNoteData.value
    }

    fun insert(dailyNoteData: DailyNoteData) = viewModelScope.launch(Dispatchers.IO) {
        littleNoteRepository.upsert(dailyNoteData)
        fetchData(dailyNoteData.noteDate)
        fetchLittleNote()
    }

}