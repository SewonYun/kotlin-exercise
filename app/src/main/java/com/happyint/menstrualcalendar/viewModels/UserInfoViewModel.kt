package com.happyint.menstrualcalendar.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.menstrualcalendar.entities.user.data.Information
import com.happyint.menstrualcalendar.entities.user.data.InformationBuilder
import com.happyint.menstrualcalendar.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserInfoViewModel(private val userRepository: UserRepository): ViewModel() {

    private var _information: MutableStateFlow<Information> =
        MutableStateFlow(InformationBuilder.getEmptyInformation())
    val information: StateFlow<Information> get() = _information.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch(Dispatchers.IO) {
        _information = MutableStateFlow(userRepository.userInformation())
        Log.d(information.toString(), "")
    }

    fun updateUserInfo(userInformation: Information) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insert(userInformation)
        fetchData().join()
    }
}