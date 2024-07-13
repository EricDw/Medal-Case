package com.challenge.medalcase.medals.viewmodel

import androidx.lifecycle.ViewModel
import com.challenge.medalcase.medals.models.Achievement
import com.challenge.medalcase.medals.models.TimeStamp
import com.challenge.medalcase.medals.screen.MedalCaseScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MedalCaseScreenViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(
        value = MedalCaseScreenState()
    )
    val stateFlow = _stateFlow.asStateFlow()

    init {
        val emptyTimeStamp = TimeStamp()

        val personalRecords = listOf(
            Achievement.PersonalRecord.LongestRun(
                completed = true,
                timeStamp = emptyTimeStamp
            ),
            Achievement.PersonalRecord.HighestElevation(
                completed = true,
                elevationInFeet = 2095,
                timeStamp = emptyTimeStamp,
            ),
            Achievement.PersonalRecord.FastestFiveK(
                completed = true,
                timeStamp = emptyTimeStamp,
            ),
            Achievement.PersonalRecord.TenK(
                completed = true,
                timeStamp = emptyTimeStamp,
            ),
            Achievement.PersonalRecord.HalfMarathon(
                completed = true,
                timeStamp = emptyTimeStamp,
            ),
            Achievement.PersonalRecord.Marathon(
                completed = false,
                timeStamp = emptyTimeStamp,
            ),
        )

        _stateFlow.update { oldState ->
            oldState.copy(
                personalRecords = personalRecords
            )
        }

    }

}
