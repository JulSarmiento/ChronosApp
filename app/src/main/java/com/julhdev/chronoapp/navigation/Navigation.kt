package com.julhdev.chronoapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.julhdev.chronoapp.viewModels.ChronometerViewModel
import com.julhdev.chronoapp.viewModels.ChronosViewModel
import com.julhdev.chronoapp.views.AddView
import com.julhdev.chronoapp.views.EditView
import com.julhdev.chronoapp.views.HomeView

@Composable
fun NavManager(chronometerViewModel: ChronometerViewModel, chronosViewModel: ChronosViewModel){
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "Home"){
    composable("Home"){
      HomeView(navController, chronosViewModel)
    }
    composable("AddView"){
      AddView(navController, chronometerViewModel, chronosViewModel)
    }
    composable("EditView"){
      EditView(navController)
    }
  }
}