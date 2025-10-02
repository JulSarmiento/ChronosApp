package com.julhdev.chronoapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

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


/**
 * A composable function that creates an icon button with a specified icon and click action.
 * The icon is displayed in white color.
 * @param icon The ImageVector representing the icon to be displayed on the button.
 * @param onClick The action to perform when the button is clicked.
 * @return A composable function that displays an icon button.
 * @Usage MainIconButton(icon = Icons.Default.YourIcon, onClick = { /* Handle click */ })
 */
@Composable
fun MainIconButton(icon: ImageVector, onClick:() -> Unit, modifier: Modifier = Modifier) {
  IconButton(onClick = onClick, modifier) {
    Icon(imageVector = icon, contentDescription = null, tint = Color.White)
  }
}



@Composable
        /**
         * A composable function that creates a circular icon button with a specified icon and click action.
         * The icon is displayed in white color.
         * @param icon The ImageVector representing the icon to be displayed on the button.
         * @param onClick The action to perform when the button is clicked.
         * @return A composable function that displays a circular icon button.
         * @Usage MainCircleIconButton(icon = Icons.Default.YourIcon, onClick = { /* Handle click */ })
         */
fun MainCircleIconButton(
  modifier: Modifier = Modifier,
  icon: ImageVector,
  enable: Boolean = false,
  onClick:() -> Unit,
) {
  IconButton(
    onClick = onClick,
    enabled = enable,
    modifier = Modifier
      .padding(horizontal = 5.dp)
      .background(if (enable) MaterialTheme.colorScheme.primary else Color.DarkGray.copy(alpha = 0.5f), shape = CircleShape)
      .then(modifier)
  ) {
    Icon(imageVector = icon, contentDescription = null, tint = if(enable) Color.White else Color.LightGray)
  }
}