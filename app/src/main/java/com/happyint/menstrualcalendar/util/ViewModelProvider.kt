package com.happyint.menstrualcalendar.util

import com.happyint.menstrualcalendar.MyApplication
import com.happyint.menstrualcalendar.viewModelFactories.UserInfoViewModelFactory
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel

object ViewModelProvider {

    private var _userInfoViewModel: UserInfoViewModel? = null
    fun getUserInfoViewModel ():UserInfoViewModel {

        if (_userInfoViewModel == null) {
            val factory = UserInfoViewModelFactory(MyApplication.instance.repositories.userRepository)
            _userInfoViewModel = factory.create(UserInfoViewModel::class.java)
        }

        return _userInfoViewModel!!
    }

}