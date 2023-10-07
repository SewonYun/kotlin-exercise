package com.happyint.menstrualcalendar.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.menstrualcalendar.entities.user.Information
import com.happyint.menstrualcalendar.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserInfoViewModel(private val userRepository: UserRepository): ViewModel() {
    private var _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private var _birth = MutableStateFlow("")
    val birth = _birth.asStateFlow()

    private var _average_cycle = MutableStateFlow(0)
    val averageCycle = _average_cycle.asStateFlow()


    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val information = userRepository.userInformation()
            _name.value = information.name ?: ""
            _birth.value = information.birth ?: ""
            _average_cycle.value = information.averageMenstrualCycle
        }
    }
    fun updateUserInfo(userInformation: Information) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.edit(userInformation)
        fetchData()
    }
}