package com.challenge.medalcase.medals.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.challenge.medalcase.medals.viewmodel.MedalCaseScreenViewModel


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

@Composable
fun MedalCaseScreen(
    modifier: Modifier = Modifier,
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

    MedalCaseScreen(
        viewModel = viewModel,
        modifier = Modifier.fillMaxSize()
    )

}