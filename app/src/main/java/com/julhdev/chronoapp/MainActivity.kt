package com.julhdev.chronoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.julhdev.chronoapp.navigation.NavManager
import com.julhdev.chronoapp.ui.theme.ChronoAppTheme
import com.julhdev.chronoapp.viewModels.ChronometerViewModel
import com.julhdev.chronoapp.viewModels.ChronosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint()
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val chronometerViewModel: ChronometerViewModel by viewModels()
    val chronosViewModel: ChronosViewModel by viewModels()
    enableEdgeToEdge()
    setContent {
      ChronoAppTheme {
        NavManager(chronometerViewModel, chronosViewModel)
      }
    }
  }
}

