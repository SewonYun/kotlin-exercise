package com.happyint.menstrualcalendar.viewModels

import androidx.lifecycle.ViewModel
import com.happyint.menstrualcalendar.entities.user.Information
import com.happyint.menstrualcalendar.repositories.InformationDao
import com.happyint.menstrualcalendar.repositories.UserRepository

class UserInfoViewModel(private val userRepository: UserRepository): ViewModel() {

    fun userInfo(): Information = userRepository.userInformation

}