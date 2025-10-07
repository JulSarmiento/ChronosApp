package com.julhdev.chronoapp.di

import android.content.Context
import androidx.room.Room
import com.julhdev.chronoapp.room.ChronosDataBase
import com.julhdev.chronoapp.room.ChronosDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
/**
 * AppModule is a Dagger Hilt module that provides application-level dependencies.
 * It includes provisions for the ChronosDataBase and ChronosDataBaseDao.
 * These dependencies are scoped as singletons to ensure a single instance throughout the app's lifecycle.
 * @see ChronosDataBase
 * @see ChronosDataBaseDao
 * @usage Inject ChronosDataBaseDao in repositories or view models to access database operations.
 */
object AppModule {

  @Singleton
  @Provides
  fun providesChronosDao(chronosDataBase: ChronosDataBase): ChronosDataBaseDao {
    return chronosDataBase.chronosDao()
  }

  @Singleton
  @Provides
  fun providesChronosDataBase(@ApplicationContext context: Context): ChronosDataBase {
    return Room.databaseBuilder(
        context,
        ChronosDataBase::class.java,
        "chronos_database"
      ).fallbackToDestructiveMigration(false).build()
  }
}