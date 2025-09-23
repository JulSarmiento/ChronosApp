package com.julhdev.chronoapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
        /**
         * A floating action button (FAB) with a plus icon.
         * This button is styled with the primary color from the Material theme and white icon color.
         * The icon used is the default "Add" icon from Material Icons.
         * @param onClick The action to perform when the button is clicked.
         * @return A composable function that displays a floating action button.
         * @Usage FloatBtn(onClick = { /* Handle click */ })
         */
fun FloatBtn(
  onClick: () -> Unit,
) {
  FloatingActionButton(
    onClick = onClick,
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = Color.White,
  ) {
    Icon(
      imageVector = Icons.Default.Add,
      contentDescription = "Add",
    )
  }
}