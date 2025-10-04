package com.julhdev.chronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.julhdev.chronoapp.components.ChronoCard
import com.julhdev.chronoapp.components.FloatBtn
import com.julhdev.chronoapp.components.MainTitle
import com.julhdev.chronoapp.components.timeFormat
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
fun HomeView(navController: NavController, chronosViewModel: ChronosViewModel) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          MainTitle(title = "Home")
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.primary
        )
      )
    },
    floatingActionButton = {
      FloatBtn(
        onClick = {
          navController.navigate("AddView")
        }
      )
    }
  ) { innerPadding ->
    ContentHomeView(innerPadding, navController, chronosViewModel)
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
fun ContentHomeView(it: PaddingValues, navController: NavController, chronosViewModel: ChronosViewModel) {
  Column(
    modifier = Modifier
      .padding(it)
      .padding(16.dp)
  ) {

    val chronos by chronosViewModel.chronosList.collectAsState()
    Text(text = if(chronos.isEmpty()) "No timers yet" else "Your timers")

    Spacer(
      modifier = Modifier
        .height(10.dp)
    )
    LazyColumn {
      items(chronos) {
        val delete = SwipeAction(
          icon = rememberVectorPainter(
            image = Icons.Default.Delete
          ),
          background = MaterialTheme.colorScheme.error,
          onSwipe = {
            chronosViewModel.deleteChrono(it)
          }
        )

        SwipeableActionsBox(
          endActions = listOf(delete),
          swipeThreshold = 270.dp
        ) {
          ChronoCard(
            title = it.title,
            time = timeFormat(it.time),
            onClick = {
              /* TODO: Navigate to Edit View with the selected chrono details */
            }
          )
        }
      }
    }
  }
}