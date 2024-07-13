package com.challenge.medalcase.medals.models

sealed class Achievement {

    sealed class PersonalRecord : Achievement() {
        abstract val completed: Boolean

        abstract val timeStamp: TimeStamp

        data class LongestRun(
            override val completed: Boolean,
            override val timeStamp: TimeStamp,
        ): PersonalRecord()

        data class HighestElevation(
            override val completed: Boolean,
            val elevationInFeet: Int,
            override val timeStamp: TimeStamp
        ): PersonalRecord()

        data class FastestFiveK(
            override val completed: Boolean,
            override val timeStamp: TimeStamp,
        ): PersonalRecord()

        data class TenK(
            override val completed: Boolean,
            override val timeStamp: TimeStamp,
        ): PersonalRecord()

        data class HalfMarathon(
            override val completed: Boolean,
            override val timeStamp: TimeStamp,
        ): PersonalRecord()

        data class Marathon(
            override val completed: Boolean,
            override val timeStamp: TimeStamp,
        ): PersonalRecord()

    }

    data class VirtualRace(
        val name: String,
        val timeStamp: TimeStamp,
    ): Achievement()
}
