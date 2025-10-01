package com.julhdev.chronoapp.reposotiries

import com.julhdev.chronoapp.model.Chrono
import com.julhdev.chronoapp.room.ChronosDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChronosRepository @Inject constructor(private val chronosDataBaseDao: ChronosDataBaseDao) {

  suspend fun addChrono(chrono: Chrono) {
    chronosDataBaseDao.insertChrono(chrono)
  }

  suspend fun updateChrono(chrono: Chrono) {
    chronosDataBaseDao.updateChrono(chrono)
  }

  suspend fun deleteChrono(chrono: Chrono) {
    chronosDataBaseDao.deleteChrono(chrono)
  }

  fun getChronos(): Flow<List<Chrono>> = chronosDataBaseDao.getChronos().flowOn(Dispatchers.IO).conflate()

  fun getChronoById(id: Long): Flow<Chrono?> = chronosDataBaseDao.getChronosById(id).flowOn(Dispatchers.IO).conflate()

}