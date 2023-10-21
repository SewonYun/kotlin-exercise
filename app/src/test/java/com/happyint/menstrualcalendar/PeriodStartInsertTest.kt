package com.happyint.menstrualcalendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.happyint.menstrualcalendar.entities.calendar.data.DayData
import com.happyint.menstrualcalendar.repositories.DayDataDao
import com.happyint.menstrualcalendar.repositories.DayDataRepository
import com.happyint.menstrualcalendar.repositories.InformationDao
import com.happyint.menstrualcalendar.viewModelFactories.CalendarViewModelFactory
import com.happyint.menstrualcalendar.viewModels.CalendarViewModel
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

        calendarViewModel =
            CalendarViewModelFactory(DayDataRepository(mockDayDataDao)).create(CalendarViewModel::class.java)

    }

    @Test
    fun testStartDateInsertWithGet(): Unit = runBlocking {
        // 테스트 데이터 설정
        val selectedDayData = DayData(0, localDate, endDate = localDate, false)
        val selectedDate: LocalDate = localDate

        calendarViewModel.upsertDayData(selectedDayData).join()


        val getDayData: DayData? = calendarViewModel.totalPeriodData.value.let { it ->
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