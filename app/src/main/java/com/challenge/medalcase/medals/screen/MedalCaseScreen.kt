package com.challenge.medalcase.medals.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.medalcase.R
import com.challenge.medalcase.medals.models.Achievement
import com.challenge.medalcase.medals.viewmodel.MedalCaseScreenViewModel
import com.challenge.medalcase.ui.theme.MedalCaseTheme
import com.challenge.medalcase.ui.theme.SecondaryLightGray
import com.challenge.medalcase.ui.theme.Teal
import com.challenge.medalcase.ui.theme.White


@Composable
fun MedalCaseScreen(
    viewModel: MedalCaseScreenViewModel,
    modifier: Modifier = Modifier,
) {

    val screenState by viewModel.stateFlow.collectAsState()

    with(screenState) {
        MedalCaseScreen(
            modifier = modifier,
            personalRecords = personalRecords,
            virtualRaces = virtualRaces
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MedalCaseScreen(
    modifier: Modifier = Modifier,
    personalRecords: List<Achievement.PersonalRecord> = emptyList(),
    virtualRaces: List<Achievement.VirtualRace> = emptyList(),
) {

    val personalRecordChunks = remember(personalRecords) {
        personalRecords.chunked(2)
    }

    val virtualRaceChunks = remember(virtualRaces) {
        virtualRaces.chunked(2)
    }

    val amountOfPersonalRecordsCompleted = remember(personalRecords) {
        var completedCount = 0
        personalRecords.forEach { personalRecord ->
            if (personalRecord.completed) {
                completedCount++
            }
        }
        completedCount
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                Modifier
                    .background(color = Teal)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_chevron_24dp),
                        contentDescription = null,
                        tint = White,
                    )
                }

                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.title_achievements),
                    style = MaterialTheme
                        .typography
                        .headlineLarge
                        .copy(color = White),
                    textAlign = TextAlign.Center,
                )

                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = White,
                    )
                }

            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            if (personalRecords.isNotEmpty()) {
                stickyHeader {
                    Row(
                        Modifier
                            .background(color = SecondaryLightGray)
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                end = 16.dp,
                                bottom = 8.dp,
                            ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_personal_records),
                            style = MaterialTheme.typography.labelLarge,
                        )

                        Spacer(modifier = Modifier.weight(1F))

                        Text(
                            text = stringResource(
                                id = R.string.label_n_of_n,
                                amountOfPersonalRecordsCompleted,
                                personalRecords.size
                            ),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }

                items(items = personalRecordChunks) { personalRecords ->
                    PersonalRecordRow(personalRecords = personalRecords)
                }
            }

            if (virtualRaces.isNotEmpty()) {
                stickyHeader {
                    Row(
                        Modifier
                            .background(color = SecondaryLightGray)
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_virtual_races),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }

                items(items = virtualRaceChunks) { virtualRaces ->
                    VirtualRaceRow(virtualRaces = virtualRaces)
                }
            }

        }

    }
}

@Composable
private fun PersonalRecordRow(
    personalRecords: List<Achievement.PersonalRecord>,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        personalRecords.forEach { personalRecord ->
            PersonalRecordItem(
                personalRecord = personalRecord
            )
        }
    }

}

@Composable
private fun RowScope.PersonalRecordItem(
    personalRecord: Achievement.PersonalRecord,
) {

    @DrawableRes
    val imageResourceId: Int

    val label: String
    var subLabel: String

    when (personalRecord) {
        is Achievement.PersonalRecord.LongestRun -> {
            imageResourceId = R.mipmap.longest_run
            label = stringResource(id = R.string.label_longest_run)
            subLabel = stringResource(
                id = R.string.label_time_hours_minutes,
                personalRecord.timeStamp.hours,
                personalRecord.timeStamp.minutes,
            )
        }

        is Achievement.PersonalRecord.HighestElevation -> {
            imageResourceId = R.mipmap.highest_elevation
            label = stringResource(id = R.string.label_highest_elevation)
            subLabel = stringResource(
                id = R.string.label_elevation_in_feet,
                personalRecord.elevationInFeet
            )
        }

        is Achievement.PersonalRecord.FastestFiveK -> {
            imageResourceId = R.mipmap.fastest_5k
            label = stringResource(id = R.string.label_Fastest_5_k)
            subLabel = stringResource(
                id = R.string.label_time_hours_minutes,
                personalRecord.timeStamp.hours,
                personalRecord.timeStamp.minutes,
            )
        }

        is Achievement.PersonalRecord.TenK -> {
            imageResourceId = R.mipmap.fastest_10k
            label = stringResource(id = R.string.label_10_k)
            subLabel = stringResource(
                id = R.string.label_time_hours_minutes_seconds,
                personalRecord.timeStamp.hours,
                personalRecord.timeStamp.minutes,
                personalRecord.timeStamp.seconds,
            )
        }

        is Achievement.PersonalRecord.HalfMarathon -> {
            imageResourceId = R.mipmap.half_marathon
            label = stringResource(id = R.string.label_half_marathon)
            subLabel = stringResource(
                id = R.string.label_time_hours_minutes,
                personalRecord.timeStamp.hours,
                personalRecord.timeStamp.minutes,
            )
        }

        is Achievement.PersonalRecord.Marathon -> {
            imageResourceId = R.mipmap.fastest_marathon
            label = stringResource(id = R.string.label_marathon)
            subLabel = stringResource(
                id = R.string.label_time_hours_minutes,
                personalRecord.timeStamp.hours,
                personalRecord.timeStamp.minutes,
            )
        }
    }

    var columnModifier =
        Modifier.weight(0.5F)

    if (!personalRecord.completed) {
        columnModifier = columnModifier.alpha(0.30F)
        subLabel = stringResource(id = R.string.label_incomplete_record)
    }

    Column(
        modifier = columnModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = imageResourceId),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = subLabel,
            style = MaterialTheme.typography.labelMedium
        )
    }

}


@Composable
private fun VirtualRaceRow(
    virtualRaces: List<Achievement.VirtualRace>,
) {

}

@Composable
private fun RowScope.VirtualRaceItem(
    virtualRace: Achievement.VirtualRace,
) {

}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun MedalCaseScreenPreview() {

    val viewModel = remember {
        MedalCaseScreenViewModel()
    }

    MedalCaseTheme {
        MedalCaseScreen(
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize()
        )
    }

}