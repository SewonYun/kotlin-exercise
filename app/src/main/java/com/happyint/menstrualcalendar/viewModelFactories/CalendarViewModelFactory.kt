package com.happyint.menstrualcalendar.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.happyint.menstrualcalendar.repositories.DayDataRepository
import com.happyint.menstrualcalendar.viewModels.CalendarViewModel
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel

class CalendarViewModelFactory(private val dayDataRepository: DayDataRepository) : ViewModelProvider
.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalendarViewModel(dayDataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}