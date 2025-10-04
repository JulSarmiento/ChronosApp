package com.julhdev.chronoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.julhdev.chronoapp.model.Chrono
import kotlinx.coroutines.flow.Flow

@Dao // Data access Observer
/**
 * Interface -> Repository -> ViewModel -> View
 * Data Access Object (DAO) for performing CRUD operations on Chrono entities in the database.
 * Provides methods to insert, update, delete, and query Chrono records.
 * Uses Kotlin Coroutines Flow for asynchronous data handling.
 * @see Flow
 * @usage val chronos: Flow<List<Chrono>> = chronosDao.getChronos()
 */
interface ChronosDataBaseDao {

  @Query("SELECT * FROM chronos")
  fun getChronos(): Flow<List<Chrono>>

  @Query("SELECT * FROM chronos WHERE id = :id")
  fun getChronosById(id: Long): Flow<Chrono?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertChrono(chrono: Chrono)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun updateChrono(chrono: Chrono)

  @Delete
  suspend fun deleteChrono(chrono: Chrono)
}