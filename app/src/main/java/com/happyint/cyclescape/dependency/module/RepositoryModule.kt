package com.happyint.cyclescape.dependency.module

import com.happyint.cyclescape.AppDatabase
import com.happyint.cyclescape.repositories.DayDataDao
import com.happyint.cyclescape.repositories.DayDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideDayDataDao(appDatabase: AppDatabase): DayDataDao {
        return appDatabase.dayDataDao()
    }

    @Provides
    fun provideDayDataRepository(dayDataDao: DayDataDao): DayDataRepository {
        return DayDataRepository(dayDataDao)
    }
}