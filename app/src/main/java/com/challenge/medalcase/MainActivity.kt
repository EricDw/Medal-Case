package com.challenge.medalcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.challenge.medalcase.medals.screen.MedalCaseScreen
import com.challenge.medalcase.medals.viewmodel.MedalCaseScreenViewModel
import com.challenge.medalcase.ui.theme.MedalCaseTheme

class MainActivity : ComponentActivity() {

    val medalCaseScreenViewModel: MedalCaseScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedalCaseTheme {
                MedalCaseScreen(
                    viewModel = medalCaseScreenViewModel
                )
            }
        }
    }
}