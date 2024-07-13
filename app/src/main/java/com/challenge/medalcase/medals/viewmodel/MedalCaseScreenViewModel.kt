package com.challenge.medalcase.medals.viewmodel

import androidx.lifecycle.ViewModel
import com.challenge.medalcase.medals.screen.MedalCaseScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MedalCaseScreenViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(MedalCaseScreenState())
    val stateFlow = _stateFlow.asStateFlow()

}
