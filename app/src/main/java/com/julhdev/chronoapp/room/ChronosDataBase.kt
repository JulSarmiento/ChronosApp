package com.julhdev.chronoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.julhdev.chronoapp.model.Chrono

@Database(
  entities = [Chrono::class],
  version = 1,
  exportSchema = false
)
/**
 * Abstract class representing the Room database for Chrono entities.
 * Provides access to the ChronosDataBaseDao for performing database operations.
 * @see ChronosDataBaseDao
 * @usage val db = Room.databaseBuilder(context, ChronosDataBase::class.java, "chronos_db").build()
 */
abstract class ChronosDataBase: RoomDatabase() {
  abstract fun  chronosDao(): ChronosDataBaseDao
}