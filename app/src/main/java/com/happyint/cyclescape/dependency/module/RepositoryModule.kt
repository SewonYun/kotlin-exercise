package com.happyint.cyclescape.dependency.module

import android.content.Context
import androidx.room.Room
import com.happyint.cyclescape.AppDatabase
import com.happyint.cyclescape.repositories.DayDataDao
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.repositories.InformationDao
import com.happyint.cyclescape.repositories.LittleNoteDao
import com.happyint.cyclescape.repositories.LittleNoteRepository
import com.happyint.cyclescape.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideDayDataDao(appDatabase: AppDatabase): DayDataDao {
        return appDatabase.dayDataDao()
    }

    @Provides
    fun provideDayDataRepository(dayDataDao: DayDataDao): DayDataRepository {
        return DayDataRepository(dayDataDao)
    }

    @Provides
    fun provideInformation(appDatabase: AppDatabase): InformationDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUserRepository(informationDao: InformationDao): UserRepository {
        return UserRepository(informationDao)
    }

    @Provides
    fun provideLittleNoteDao(appDatabase: AppDatabase): LittleNoteDao {
        return appDatabase.littleNoteDao()
    }

    @Provides
    fun provideLittleNoteRepository(littleNoteDao: LittleNoteDao): LittleNoteRepository {
        return LittleNoteRepository(littleNoteDao)
    }
}