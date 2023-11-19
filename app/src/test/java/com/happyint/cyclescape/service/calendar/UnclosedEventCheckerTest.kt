package com.happyint.cyclescape.service.calendar

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.happyint.cyclescape.AppDatabase
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.repositories.DayDataDao
import com.happyint.cyclescape.repositories.DayDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.time.LocalDate

@RunWith(RobolectricTestRunner::class)
class UnclosedEventCheckerTest {

    lateinit var mockDatabase: AppDatabase
    lateinit var mockDayDataDao: DayDataDao
    lateinit var mockDataRepository: DayDataRepository

    @Before
    fun setup() {
        mockDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        mockDayDataDao = mockDatabase.dayDataDao()
        mockDataRepository = DayDataRepository(mockDayDataDao)

        CoroutineScope(Dispatchers.IO).launch {

            mockDataRepository.upsert(
                DayData(
                    id = 0,
                    startDate = LocalDate.now().minusDays(1),
                    endDate = null,
                    hasLittleNote = false
                )
            )

        }

    }

    @Test
    fun findUnClosedEvents(): Unit = runBlocking(Dispatchers.IO) {

        val checker = UnclosedEventChecker(mockDataRepository)
        val result = checker.findUnClosedEvents(LocalDate.now())

        assertEquals(ProcessingResult.Success, result)

    }

    @After
    fun last() {
        mockDatabase.close()
    }

}