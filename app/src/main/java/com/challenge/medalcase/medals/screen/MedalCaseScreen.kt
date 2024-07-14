package com.challenge.medalcase.medals.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.challenge.medalcase.R
import com.challenge.medalcase.medals.models.Achievement
import com.challenge.medalcase.medals.models.TimeStamp
import com.challenge.medalcase.medals.samples.ImageURL
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
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                end = 16.dp,
                                bottom = 8.dp,
                            ),
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

    val timeStampLabel = getTimeStampLabel(timestamp = personalRecord.timeStamp)

    when (personalRecord) {
        is Achievement.PersonalRecord.LongestRun       -> {
            imageResourceId = R.drawable.longest_run
            label = stringResource(id = R.string.label_longest_run)
            subLabel = timeStampLabel
        }

        is Achievement.PersonalRecord.HighestElevation -> {
            imageResourceId = R.drawable.highest_elevation
            label = stringResource(id = R.string.label_highest_elevation)
            subLabel = stringResource(
                id = R.string.label_elevation_in_feet,
                personalRecord.elevationInFeet
            )
        }

        is Achievement.PersonalRecord.FastestFiveK     -> {
            imageResourceId = R.drawable.fastest_5k
            label = stringResource(id = R.string.label_Fastest_5_k)
            subLabel = timeStampLabel
        }

        is Achievement.PersonalRecord.TenK             -> {
            imageResourceId = R.drawable.fastest_10k
            label = stringResource(id = R.string.label_10_k)
            subLabel = timeStampLabel
        }

        is Achievement.PersonalRecord.HalfMarathon     -> {
            imageResourceId = R.drawable.half_marathon
            label = stringResource(id = R.string.label_half_marathon)
            subLabel = timeStampLabel
        }

        is Achievement.PersonalRecord.Marathon         -> {
            imageResourceId = R.drawable.fastest_marathon
            label = stringResource(id = R.string.label_marathon)
            subLabel = timeStampLabel
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

        Box(
            modifier = Modifier.size(100.dp),
        ) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        }

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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        virtualRaces.forEach { virtualRace ->
            VirtualRaceItem(
                virtualRace = virtualRace
            )
        }
    }
}

@Composable
private fun RowScope.VirtualRaceItem(
    virtualRace: Achievement.VirtualRace,
) {

    @DrawableRes
    val imageResourceId = when (virtualRace.imageURL) {
        ImageURL.VIRTUAL_HALF_MARATHON_RACE   -> {
            R.drawable.virtual_half_marathon_race
        }

        ImageURL.TOKYO_HAKONE_EKIDEN_2020     -> {
            R.drawable.tokyo_kakone_ekiden
        }

        ImageURL.VIRTUAL_10_K_RACE            -> {
            R.drawable.virtual_10k_race
        }

        ImageURL.HAKONE_EKIDEN                -> {
            R.drawable.hakone_ekiden
        }

        ImageURL.MIZUNO_SINGAPORE_EKIDEN_2015 -> {
            R.drawable.mizuno_singapore_ekiden
        }

        ImageURL.VIRTUAL_5_K_RACE             -> {
            R.drawable.virtual_5k_race
        }
    }


    Column(
        modifier = Modifier.weight(0.5F),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = imageResourceId),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.sizeIn(maxWidth = 150.dp),
            text = virtualRace.name,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = getTimeStampLabel(timestamp = virtualRace.timeStamp),
            style = MaterialTheme.typography.labelMedium,
        )
    }

}

@Composable
private fun getTimeStampLabel(timestamp: TimeStamp): String {
    return with(timestamp) {
        if (seconds != null) {
            stringResource(
                id = com.challenge.medalcase.R.string.label_time_hours_minutes_seconds,
                hours,
                minutes,
                seconds,
            )
        } else {
            stringResource(
                id = com.challenge.medalcase.R.string.label_time_hours_minutes,
                hours,
                minutes,
            )
        }
    }
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