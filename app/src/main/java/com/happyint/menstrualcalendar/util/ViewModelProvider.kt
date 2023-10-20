package com.happyint.menstrualcalendar.util

import com.happyint.menstrualcalendar.AppDatabase
import com.happyint.menstrualcalendar.PeriodApplication
import com.happyint.menstrualcalendar.repositories.DayDataRepository
import com.happyint.menstrualcalendar.repositories.UserRepository
import com.happyint.menstrualcalendar.viewModelFactories.UserInfoViewModelFactory
import com.happyint.menstrualcalendar.viewModels.CalendarViewModel
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel

object ViewModelProvider {

    private var _userInfoViewModel: UserInfoViewModel? = null
    private var _calendarViewModel: CalendarViewModel? = null
    private val database: AppDatabase = PeriodApplication.instance.database
    fun getUserInfoViewModel(): UserInfoViewModel {

        if (_userInfoViewModel == null) {
            val factory =
                UserInfoViewModelFactory(UserRepository(database.userDao()))
            _userInfoViewModel = factory.create(UserInfoViewModel::class.java)
        }

        return _userInfoViewModel!!
    }

    fun getCalendarViewModel(): CalendarViewModel {
        if (_calendarViewModel == null) {
            _calendarViewModel =
                CalendarViewModel(DayDataRepository(database.dayDataDao()))
        }

        return _calendarViewModel!!
    }

}