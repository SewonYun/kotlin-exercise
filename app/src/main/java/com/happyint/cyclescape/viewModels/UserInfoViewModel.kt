package com.happyint.cyclescape.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happyint.cyclescape.entities.user.data.Information
import com.happyint.cyclescape.entities.user.data.InformationBuilder
import com.happyint.cyclescape.repositories.UserRepository
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
        _information.value = userRepository.userInformation()
    }

    fun updateUserInfo(userInformation: Information) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insert(userInformation)
        fetchData().join()
    }
}