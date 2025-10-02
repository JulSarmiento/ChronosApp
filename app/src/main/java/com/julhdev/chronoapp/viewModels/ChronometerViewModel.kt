package com.julhdev.chronoapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julhdev.chronoapp.state.ChronoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * ChronometerViewModel is a ViewModel that manages the state and operations related to a chronometer.
 * It holds the current state of the chronometer, including whether it is active, the elapsed time,
 * and the title associated with the chronometer. It also manages a Job for handling the
 * chronometer's timing operations.
 * @see ChronoState
 * @usage Inject ChronometerViewModel in UI components to observe and manipulate chronometer state.
 */
class ChronometerViewModel: ViewModel() {

  var state by mutableStateOf(ChronoState())
    private set

  var chronoJob by mutableStateOf<Job?>(null)
    private set

  var time by mutableLongStateOf(0L)
    private set

  fun onValue(value: String) {
    state = state.copy(title = value)
  }

  /**
   * Starts the chronometer by setting the chronometerActive state to true.
   * This function can be called to initiate the timing operation of the chronometer.
   * It does not handle the actual timing logic, which should be managed separately,
   * typically using a coroutine Job.
   */
  fun onStart(){
    state = state.copy(chronometerActive = true)
  }

  /**
   * Pauses the chronometer by setting the chronometerActive state to false,
   * and updates the state to show the save button.
   * This function can be called to temporarily halt the timing operation of the chronometer
   * without resetting the elapsed time.
   */
  fun onPause(){
    state = state.copy(chronometerActive = false, showSaveBtn = true)
  }

  /**
   * Stops the chronometer by setting the chronometerActive state to false,
   * and updates the state to show the save button and text field.
   * It also cancels any ongoing timing Job associated with the chronometer.
   * This function can be called to halt the timing operation of the chronometer.
   */
  fun onStop(){
    chronoJob?.cancel()
    time = 0L
    state = state.copy(chronometerActive = false, showSaveBtn = false, showTextField = false)
  }

  /** Hides the text field by updating the state to set showTextField to false.
   * This function can be called when the text field is no longer needed or should be hidden from the UI.
   */
  fun shotTextField(){
    state = state.copy(showTextField = true)
  }

  /**
   * Manages the chronometer's timing operations based on the current state.
   * If the chronometer is active, it starts a coroutine Job that increments the elapsed time
   * every second. If the chronometer is not active, it cancels any ongoing timing Job.
   * This function should be called whenever there is a change in the chronometerActive state
   * to ensure the timing operations are correctly started or stopped.
   */
  fun chronos() {
    if(state.chronometerActive){
      chronoJob?.cancel()
      chronoJob = viewModelScope.launch {
        while(true){
          time += 1000L
          delay(1000L)
        }
      }
    } else {
      chronoJob?.cancel()
    }
  }

}