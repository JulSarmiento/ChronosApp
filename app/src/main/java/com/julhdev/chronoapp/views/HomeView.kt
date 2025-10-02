package com.julhdev.chronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.julhdev.chronoapp.components.FloatBtn
import com.julhdev.chronoapp.components.MainTitle

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
fun HomeView(navController: NavController) {
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
    ContentHomeView(innerPadding, navController)
  }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController) {
  Column(
    modifier = Modifier
      .padding(it)
      .padding(16.dp)
  ) {
    Text(text = "Home View")
  }
}