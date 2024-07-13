package com.challenge.medalcase.medals.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.medalcase.R
import com.challenge.medalcase.medals.viewmodel.MedalCaseScreenViewModel
import com.challenge.medalcase.ui.theme.MedalCaseTheme
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
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedalCaseScreen(
    modifier: Modifier = Modifier,
) {
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