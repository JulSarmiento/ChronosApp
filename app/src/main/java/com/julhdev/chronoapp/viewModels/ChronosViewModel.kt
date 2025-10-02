package com.julhdev.chronoapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julhdev.chronoapp.model.Chrono
import com.julhdev.chronoapp.reposotiries.ChronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
/**
 * ChronosViewModel is a ViewModel that manages the state and operations related to Chrono entities.
 * It interacts with the ChronosRepository to perform CRUD operations and exposes a StateFlow of the list of Chronos.
 * @property repository The ChronosRepository used for data operations.
 * @see ChronosRepository
 * @see Chrono
 * @usage Inject ChronosViewModel in UI components to observe and manipulate Chrono data.
 */
class ChronosViewModel @Inject constructor(private val repository: ChronosRepository): ViewModel() {

  private val _chronosList = MutableStateFlow<List<Chrono>>(emptyList())
  val chronosList = _chronosList.asStateFlow()

  init {
    viewModelScope.launch(Dispatchers.IO) {
      repository.getChronos().collect { item ->
        if (item.isEmpty()) {
          _chronosList.value = emptyList()
        } else {
          _chronosList.value = item
        }
      }
    }
  }

  fun addChrono( chrono: Chrono) {
    viewModelScope.launch {
      repository.addChrono(chrono)
    }
  }

  fun updateChrono( chrono: Chrono) {
    viewModelScope.launch {
      repository.updateChrono(chrono)
    }
  }

  fun deleteChrono( chrono: Chrono) {
    viewModelScope.launch {
      repository.deleteChrono(chrono)
    }
  }

}