package com.julhdev.chronoapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.julhdev.chronoapp.components.FloatBtn
import com.julhdev.chronoapp.components.MainCircleIconButton
import com.julhdev.chronoapp.components.MainIconButton
import com.julhdev.chronoapp.components.MainTitle
import com.julhdev.chronoapp.components.timeFormat
import com.julhdev.chronoapp.viewModels.ChronometerViewModel

@OptIn(ExperimentalMaterial3Api::class)
/**
 * A composable function that represents the Add view of the application.
 * It displays a simple text indicating that this is the Add View.
 *
 * @param navController The NavController used for navigation between different views.
 * @return A composable function that renders the Add view.
 * @usage AddView(navController = navController)
 */
@Composable
fun AddView(navController: NavController, chronometerViewModel: ChronometerViewModel) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          MainTitle(title = "New Timer" )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = { MainIconButton(
          icon = Icons.AutoMirrored.Filled.ArrowBack,
          onClick = { navController.popBackStack() }
        ) }
      )
    }
  ) { innerPadding ->
    ContentAddView(innerPadding, navController, chronometerViewModel)
  }
}


@Composable
fun ContentAddView(it: PaddingValues, navController: NavController, chronometerViewModel: ChronometerViewModel) {

  val state = chronometerViewModel.state

  LaunchedEffect(
    state.chronometerActive,
  ) {
    chronometerViewModel.chronos()
  }

  Column(
    modifier = androidx.compose.ui.Modifier
      .padding(it)
      .padding(16.dp)
      .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Text(
      text = timeFormat(chronometerViewModel.time),
      fontSize = 50.sp,
      fontWeight = FontWeight.Bold
    )

    Row(
      modifier = Modifier
        .padding(top = 10.dp)
    ) {

      MainCircleIconButton(
        icon = Icons.Default.PlayArrow,
        enable = !state.chronometerActive,
        onClick = { chronometerViewModel.onStart() },
      )

      MainCircleIconButton(
        icon = Icons.Default.Pause,
        enable = state.chronometerActive,
        onClick = { chronometerViewModel.onPause() },
      )

      MainCircleIconButton(
        icon = Icons.Default.Stop,
        enable = state.chronometerActive || chronometerViewModel.time != 0L,
        onClick = { chronometerViewModel.onStop() },
      )

      MainCircleIconButton(
        icon = Icons.Default.Save,
        enable = !state.chronometerActive && chronometerViewModel.time != 0L,
        onClick = { /* TODO */ },
      )
    }
  }
}