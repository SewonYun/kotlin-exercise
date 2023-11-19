package com.happyint.cyclescape.util

import com.happyint.cyclescape.AppDatabase
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.repositories.UserRepository
import com.happyint.cyclescape.viewModelFactories.UserInfoViewModelFactory
import com.happyint.cyclescape.viewModels.CalendarViewModel
import com.happyint.cyclescape.viewModels.UserInfoViewModel

object ViewModelProvider {

    private var _userInfoViewModel: UserInfoViewModel? = null
    private var _calendarViewModel: CalendarViewModel? = null
    private val database: AppDatabase = CycleScapeApplication.instance.database
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