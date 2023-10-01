package com.happyint.menstrualcalendar.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.menstrualcalendar.entities.user.Information
import com.happyint.menstrualcalendar.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserInfoViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _userInfo = MutableStateFlow(Information(id = 0, name = "", birth = ""))

    val userInfo: StateFlow<Information> = _userInfo.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _userInfo.value = userRepository.userInformation()
        }
    }

    fun updateUserInfo(userInformation: Information) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.edit(userInformation)
    }
}