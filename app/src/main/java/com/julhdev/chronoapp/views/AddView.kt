package com.julhdev.chronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.julhdev.chronoapp.components.MainCircleIconButton
import com.julhdev.chronoapp.components.MainIconButton
import com.julhdev.chronoapp.components.MainTextField
import com.julhdev.chronoapp.components.MainTitle
import com.julhdev.chronoapp.components.timeFormat
import com.julhdev.chronoapp.model.Chrono
import com.julhdev.chronoapp.viewModels.ChronometerViewModel
import com.julhdev.chronoapp.viewModels.ChronosViewModel

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
fun AddView(navController: NavController, chronometerViewModel: ChronometerViewModel, chronosViewModel: ChronosViewModel) {
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
    ContentAddView(innerPadding, navController, chronometerViewModel, chronosViewModel)
  }
}


@Composable
/**
 * A composable function that represents the content of the Add view.
 * It displays a chronometer with start, pause, stop, and save buttons.
 *
 * @param it The padding values to be applied to the content.
 * @param navController The NavController used for navigation between different views.
 * @param chronometerViewModel The ViewModel managing the state of the chronometer.
 * @return A composable function that renders the content of the Add view.
 * @usage ContentAddView(it = paddingValues, navController = navController, chronometerViewModel = viewModel)
 */
fun ContentAddView(
  it: PaddingValues,
  navController: NavController,
  chronometerViewModel: ChronometerViewModel,
  chronosViewModel: ChronosViewModel
) {
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
        onClick = { chronometerViewModel.shotTextField() },
      )
    }

    Spacer(
      modifier = Modifier
        .height(20.dp)
    )

    if(state.showTextField) {
      MainTextField(
        value = state.title,
        onValueChange = { chronometerViewModel.onValue(it) },
        label = "Title"
      )

      Button(
        onClick = {
          chronosViewModel.addChrono(
            Chrono(
              title = state.title,
              time = chronometerViewModel.time
            )
          )
          chronometerViewModel.onStop()
          navController.popBackStack()
        }
      ) {
        Text(text = "Guardar")
      }
    }
  }
}
