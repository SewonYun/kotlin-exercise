package com.happyint.menstrualcalendar.util

import com.happyint.menstrualcalendar.PeriodApplication
import com.happyint.menstrualcalendar.viewModelFactories.UserInfoViewModelFactory
import com.happyint.menstrualcalendar.viewModels.CalendarViewModel
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel

object ViewModelProvider {

    private var _userInfoViewModel: UserInfoViewModel? = null
    private var _calendarViewModel: CalendarViewModel? = null
    fun getUserInfoViewModel(): UserInfoViewModel {

        if (_userInfoViewModel == null) {
            val factory =
                UserInfoViewModelFactory(PeriodApplication.instance.repositories.userRepository)
            _userInfoViewModel = factory.create(UserInfoViewModel::class.java)
        }

        return _userInfoViewModel!!
    }

    fun getCalendarViewModel(): CalendarViewModel {
        if (_calendarViewModel == null) {
            _calendarViewModel =
                CalendarViewModel(PeriodApplication.instance.repositories.dayDataRepository)
        }

        return _calendarViewModel!!
    }

}