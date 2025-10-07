package com.julhdev.chronoapp.state

/**
 * Data class representing the state of a chronometer.
 * @property chronometerActive A Boolean indicating whether the chronometer is active or not. Default is false.
 * @property showSaveBtn A Boolean indicating whether to show the save button. Default is false.
 * @property showTextField A Boolean indicating whether to show the text field. Default is false.
 * @property title A String representing the title associated with the chronometer. Default is an empty string.
 * @constructor Creates a new instance of [ChronoState] with the specified properties.
 * @usage val chronoState = ChronoState(chronometerActive = true, showSaveBtn = true, showTextField = false, title = "My Chrono")
 */
data class ChronoState(
  val chronometerActive: Boolean = false,
  val showSaveBtn: Boolean = false,
  val showTextField: Boolean = false,
  val title: String = ""
)
