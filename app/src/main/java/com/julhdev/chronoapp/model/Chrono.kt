package com.julhdev.chronoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chronos")
/**
 * Data class representing a Chrono entity in the database.
 * @property id Unique identifier for the Chrono (auto-generated).
 * @property title Title of the Chrono.
 * @property time Time associated with the Chrono in milliseconds.
 * @usage val chrono = Chrono(title = "My Chrono", time = 60000L)
 */
data class Chrono(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,

  @ColumnInfo(name = "title")
  val title : String,

  @ColumnInfo(name = "time")
  val time: Long,
)
