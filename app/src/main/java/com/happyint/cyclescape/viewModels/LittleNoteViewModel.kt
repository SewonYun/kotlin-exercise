package com.happyint.cyclescape.viewModels

import androidx.lifecycle.ViewModel
import com.happyint.cyclescape.entities.user.data.Information
import com.happyint.cyclescape.entities.user.data.InformationBuilder
import com.happyint.cyclescape.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LittleNoteViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private var _information: MutableStateFlow<Information> =
        MutableStateFlow(InformationBuilder.getEmptyInformation())
    val information: StateFlow<Information> get() = _information.asStateFlow()

}