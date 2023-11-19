package com.happyint.cyclescape.service.calendar

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.happyint.cyclescape.AppDatabase
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.repositories.DayDataDao
import com.happyint.cyclescape.repositories.DayDataRepository
import kotlinx.coroutines.Dispatchers
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

    private lateinit var mockDatabase: AppDatabase
    private lateinit var mockDayDataDao: DayDataDao
    private lateinit var mockDataRepository: DayDataRepository

    @Before
    fun setup() {
        mockDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        mockDayDataDao = mockDatabase.dayDataDao()
        mockDataRepository = DayDataRepository(mockDayDataDao)

        mockDataRepository.upsert(
            DayData(
                id = 0,
                startDate = LocalDate.now().minusDays(1),
                endDate = null,
                hasLittleNote = false
            )
        )

    }

    @Test
    fun findUnClosedEvents(): Unit = runBlocking(Dispatchers.IO) {

        val checker = UnclosedEventChecker(mockDataRepository)
        val result = checker.checkUnClosedEvents(LocalDate.now())

        assertEquals(ProcessingResult.Success, result)

    }

    @Test
    fun getUnClosedEvents(): Unit = runBlocking(Dispatchers.IO) {

        val checker = UnclosedEventChecker(mockDataRepository)
        checker.checkUnClosedEvents(LocalDate.now())
        val result = checker.getUnClosedEvents()

        assertEquals(1, result.size)
        val firstDay = result.first()
        assertEquals(firstDay.startDate, LocalDate.now().minusDays(1))
        assertEquals(firstDay.endDate, null)
        assertEquals(firstDay.hasLittleNote, false)

    }

    @Test(expected = IllegalAccessException::class)
    fun errorTest(): Unit = runBlocking(Dispatchers.IO) {

        val checker = UnclosedEventChecker(mockDataRepository)
        checker.getUnClosedEvents()

    }

    @After
    fun tearDown() {
        mockDatabase.close()
    }

}