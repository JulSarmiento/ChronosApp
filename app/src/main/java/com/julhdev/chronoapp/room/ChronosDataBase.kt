package com.julhdev.chronoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.julhdev.chronoapp.model.Chrono

@Database(
  entities = [Chrono::class],
  version = 1,
  exportSchema = false
)
abstract class ChronosDataBase: RoomDatabase() {
  abstract fun  chronosDao(): ChronosDataBaseDao
}