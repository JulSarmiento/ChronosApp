package com.julhdev.chronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.julhdev.chronoapp.components.ChronoCard
import com.julhdev.chronoapp.components.FloatBtn
import com.julhdev.chronoapp.components.MainCircleIconButton
import com.julhdev.chronoapp.components.MainIconButton
import com.julhdev.chronoapp.components.MainTextField
import com.julhdev.chronoapp.components.MainTitle
import com.julhdev.chronoapp.components.timeFormat
import com.julhdev.chronoapp.model.Chrono
import com.julhdev.chronoapp.viewModels.ChronometerViewModel
import com.julhdev.chronoapp.viewModels.ChronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /**
         * A composable function that represents the Home view of the application.
         * It displays a simple text indicating that this is the Home View.
         *
         * @param navController The NavController used for navigation between different views.
         * @return A composable function that renders the Home view.
         * @usage HomeView(innerPadding = PaddingValues(16.dp), navController = navController
         */
fun EditView(
  navController: NavController,
  chronometerViewModel: ChronometerViewModel,
  chronosViewModel: ChronosViewModel,
  id: Long
) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          MainTitle(title = "Edit Timers")
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = { MainIconButton(
          icon = Icons.AutoMirrored.Filled.ArrowBack,
          onClick = { navController.popBackStack() }
        ) }
      )
    },
  ) { innerPadding ->
    ContentEditView(innerPadding, navController, chronometerViewModel, chronosViewModel, id)
  }
}

@Composable
        /**
         * A composable function that represents the content of the Home view.
         * It displays a list of chronometers and allows navigation to add new ones.
         *
         * @param it The padding values to be applied to the content.
         * @param navController The NavController used for navigation between different views.
         * @param chronosViewModel The ViewModel that manages the list of chronometers.
         * @return A composable function that renders the content of the Home view.
         * @usage ContentHomeView(it = PaddingValues(16.dp), navController = navController, chronosViewModel = chronosViewModel)
         */
fun ContentEditView(
  it: PaddingValues,
  navController: NavController,
  chronometerViewModel: ChronometerViewModel,
  chronosViewModel: ChronosViewModel,
  id: Long
) {
  val state = chronometerViewModel.state

  LaunchedEffect(
    state.chronometerActive,
  ) {
    chronometerViewModel.chronos()
  }

  LaunchedEffect(Unit) {
    chronometerViewModel.getChronoById(id)
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
    }

    Spacer(
      modifier = Modifier
        .height(20.dp)
    )

    MainTextField(
      value = state.title,
      onValueChange = { chronometerViewModel.onValue(it) },
      label = "Title"
    )

    Button(
      onClick = {
        chronosViewModel.updateChrono(
          Chrono(
            id = id,
            title = state.title,
            time = chronometerViewModel.time
          )
        )
        chronometerViewModel.onStop()
        navController.popBackStack()
      }
    ) {
      Text(text = "Actualizar")
    }

    DisposableEffect(Unit) {
      onDispose {
        chronometerViewModel.onStop()
      }
    }
  }
}