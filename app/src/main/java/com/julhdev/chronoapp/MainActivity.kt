package com.julhdev.chronoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.julhdev.chronoapp.navigation.NavManager
import com.julhdev.chronoapp.ui.theme.ChronoAppTheme
import com.julhdev.chronoapp.viewModels.ChronometerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint()
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val chronometerViewModel: ChronometerViewModel by viewModels()
    enableEdgeToEdge()
    setContent {
      ChronoAppTheme {
        NavManager(chronometerViewModel)
      }
    }
  }
}

