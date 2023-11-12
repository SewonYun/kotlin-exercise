package com.happyint.cyclescape.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.viewModels.CalendarViewModel

class CalendarViewModelFactory(private val dayDataRepository: DayDataRepository) : ViewModelProvider
.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalendarViewModel(dayDataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}