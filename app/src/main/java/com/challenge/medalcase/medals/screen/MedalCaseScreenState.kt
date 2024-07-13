package com.challenge.medalcase.medals.screen

import com.challenge.medalcase.medals.models.Achievement

data class MedalCaseScreenState(
    val personalRecords: List<Achievement.PersonalRecord> = emptyList(),
    val virtualRaces: List<Achievement.VirtualRace> = emptyList(),
)
