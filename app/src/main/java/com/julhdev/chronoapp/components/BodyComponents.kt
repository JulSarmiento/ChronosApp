package com.julhdev.chronoapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
        /**
         * A composable function that displays a main title text with specific styling.
         * @param title The text to be displayed as the main title.
         * @return A composable function that renders the title text with white color and bold font weight.
         * @usage MainTitle(title = "My App Title")
         */
fun MainTitle(title: String) {
  Text(
    text = title,
    color = Color.White,
    fontWeight = FontWeight.Bold
  )
}

@Composable
        /**
         * A reusable text field component with outlined style.
         * @param value The current text value of the text field.
         * @param onValueChange A lambda function that gets called when the text value changes.
         * @param label The label to be displayed inside the text field.
         * @return A composable function that renders an outlined text field with the specified parameters.
         * @usage MainTextField(value = "Hello", onValueChange = { /* Handle text change */ }, label = "Enter text")
         */
fun MainTextField(
  value : String,
  onValueChange: (String) -> Unit,
  label: String
){
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = { Text(text = label) },
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 30.dp)
      .padding(bottom = 15.dp)
  )
}


@Composable
        /**
         * Formats a given time in milliseconds into a string representation in the format "HH:MM:SS.SS".
         * @param time The time in milliseconds to be formatted.
         * @return A string representing the formatted time.
         * @usage val formattedTime = TimeFormat(3661000) // "01:01:01.00"
         */
fun timeFormat(time: Long): String {
  val hours = time / 3600000
  val minutes = (time % 3600000) / 60000
  val seconds = (time % 60000) / 1000

  return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
}


@Composable
        /**
         * A composable function that represents a clickable card displaying a title and time.
         * @param title The title text to be displayed on the card.
         * @param time The time text to be displayed on the card.
         * @param onClick A lambda function that gets called when the card is clicked.
         * @return A composable function that renders a clickable card with the specified title and time.
         * @usage Card(title = "My Timer", time = "00:05:00", onClick = { /* Handle click */ })
         */
fun ChronoCard(
  title: String,
  time: String,
  onClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 10.dp)
      .clickable { onClick() }
  ){
    Column(
      modifier = Modifier
        .padding(15.dp)
    ) {
      Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(
        modifier = Modifier
          .padding(top = 5.dp)
      )
      Row {
        Icon(
          imageVector = Icons.Default.Timer,
          contentDescription = "Timer Icon",
          tint = Color.Gray
        )

        Text(
          text = time,
          fontSize = 16.sp,
          color = Color.Gray,
          modifier = Modifier
            .padding(start = 5.dp)
        )
      }
      HorizontalDivider(
        modifier = Modifier
          .padding(top = 10.dp)
          .fillMaxWidth(),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.primary
      )
    }
  }
}