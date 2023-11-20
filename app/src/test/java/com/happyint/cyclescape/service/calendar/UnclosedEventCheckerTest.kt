package com.happyint.cyclescape.service.calendar

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import arrow.core.getOrElse
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
        val result = checker.findByDay(LocalDate.now()).getOrNull()

        assertEquals(
            DayData(
                id = 0,
                startDate = LocalDate.now().minusDays(1),
                endDate = null,
                hasLittleNote = false
            ), result
        )

    }

    @Test
    fun getUnClosedEvents(): Unit = runBlocking(Dispatchers.IO) {

        val checker = UnclosedEventChecker(mockDataRepository)
        val result = checker.findByDay(day = LocalDate.now())

        val optionResult = result.getOrElse { listOf() }
        assertEquals(1, optionResult.size)

        optionResult.first().let {
            assertEquals(it.startDate, LocalDate.now().minusDays(1))
            assertEquals(it.endDate, null)
            assertEquals(it.hasLittleNote, false)
        }

    }

    @After
    fun tearDown() {
        mockDatabase.close()
    }

}