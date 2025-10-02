package com.julhdev.chronoapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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