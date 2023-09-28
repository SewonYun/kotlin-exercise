package com.happyint.menstrualcalendar.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.happyint.menstrualcalendar.repositories.InformationDao
import com.happyint.menstrualcalendar.repositories.UserRepository
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel

class UserInfoViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserInfoViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}