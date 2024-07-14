package com.challenge.medalcase.medals.viewmodel

import androidx.lifecycle.ViewModel
import com.challenge.medalcase.medals.models.Achievement
import com.challenge.medalcase.medals.models.TimeStamp
import com.challenge.medalcase.medals.samples.ImageURL
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
        val emptyTimeStampWithSeconds = TimeStamp(seconds = "00")

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
                timeStamp = emptyTimeStampWithSeconds,
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

        val virtualRaces = listOf(
            Achievement.VirtualRace(
                imageURL = ImageURL.VIRTUAL_HALF_MARATHON_RACE,
                name = "Virtual Half Marathon Race",
                timeStamp = emptyTimeStamp,
            ),
            Achievement.VirtualRace(
                imageURL = ImageURL.TOKYO_HAKONE_EKIDEN_2020,
                name = "Tokyo-Hakone Ekiden 2020",
                timeStamp = emptyTimeStampWithSeconds,
            ),
            Achievement.VirtualRace(
                imageURL = ImageURL.VIRTUAL_10_K_RACE,
                name = "Virtual 10k Race",
                timeStamp = emptyTimeStampWithSeconds,
            ),
            Achievement.VirtualRace(
                imageURL = ImageURL.HAKONE_EKIDEN,
                name = "Hakone Ekiden",
                timeStamp = emptyTimeStampWithSeconds,
            ),
            Achievement.VirtualRace(
                imageURL = ImageURL.MIZUNO_SINGAPORE_EKIDEN_2015,
                name = "Mizuno Singapore Ekiden 2015",
                timeStamp = emptyTimeStampWithSeconds,
            ),
            Achievement.VirtualRace(
                imageURL = ImageURL.VIRTUAL_5_K_RACE,
                name = "Virtual 5K Race",
                timeStamp = TimeStamp(
                    hours = "23",
                    minutes = "07"
                ),
            ),
        )

        _stateFlow.update { oldState ->
            oldState.copy(
                personalRecords = personalRecords,
                virtualRaces = virtualRaces,
            )
        }

    }

}
