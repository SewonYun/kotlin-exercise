package com.happyint.cyclescape

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.repositories.DayDataDao
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.repositories.InformationDao
import com.happyint.cyclescape.service.calendar.UnclosedEventChecker
import com.happyint.cyclescape.viewModels.CalendarViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.ParameterizedRobolectricTestRunner.Parameter
import java.time.LocalDate

@ExperimentalMaterial3Api
@RunWith(ParameterizedRobolectricTestRunner::class)
class PeriodStartInsertTest {

    @Parameter
    lateinit var localDate: LocalDate

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data() = listOf(
            LocalDate.parse("2023-01-01")
        )
    }

    private lateinit var mockDatabase: AppDatabase
    private lateinit var mockInformationDao: InformationDao
    private lateinit var mockDayDataDao: DayDataDao
    private lateinit var calendarViewModel: CalendarViewModel

    @Before
    fun setup() {
        mockDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        mockInformationDao = mockDatabase.userDao()
        mockDayDataDao = mockDatabase.dayDataDao()
        val dayDataRepository = DayDataRepository(mockDayDataDao)
        calendarViewModel = CalendarViewModel(
            DayDataRepository(mockDayDataDao),
            UnclosedEventChecker(dayDataRepository)
        )
    }

    @Test
    fun testStartDateInsertWithGet(): Unit = runBlocking {
        // 테스트 데이터 설정
        val selectedDayData = DayData(0, localDate, endDate = localDate, false)
        val selectedDate: LocalDate = localDate

        calendarViewModel.upsertDayData(selectedDayData).join()


        val getDayData: DayData? = calendarViewModel.monthPeriodData.value.let { it ->
            it.lastOrNull {
                it.startDate == localDate
            }
        }

        assert((selectedDate == getDayData?.startDate))
    }

    @After
    fun tearDown() {
        mockDatabase.close()
    }
}