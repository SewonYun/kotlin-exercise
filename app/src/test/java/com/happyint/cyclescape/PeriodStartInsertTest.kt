package com.happyint.cyclescape

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.repositories.DayDataDao
import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.repositories.InformationDao
import com.happyint.cyclescape.repositories.LittleNoteDao
import com.happyint.cyclescape.repositories.LittleNoteRepository
import com.happyint.cyclescape.service.calendar.CalendarDialogPage
import com.happyint.cyclescape.service.calendar.EventPeriodChecker
import com.happyint.cyclescape.service.calendar.UnclosedEventChecker
import com.happyint.cyclescape.viewModels.CalendarViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
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
    private lateinit var littleNoteDao: LittleNoteDao
    private lateinit var calendarViewModel: CalendarViewModel

    @Before
    fun setup() {
        mockDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        mockInformationDao = mockDatabase.userDao()
        mockDayDataDao = mockDatabase.dayDataDao()
        littleNoteDao = mockDatabase.littleNoteDao()

        val dayDataRepository = DayDataRepository(mockDayDataDao)

        calendarViewModel = CalendarViewModel(
            DayDataRepository(mockDayDataDao),
            LittleNoteRepository(littleNoteDao),
            UnclosedEventChecker(dayDataRepository),
            EventPeriodChecker(dayDataRepository)
        )

    }

    @Test
    fun testStartDateInsertWithGet(): Unit = runBlocking {
        // 테스트 데이터 설정
        val selectedDayData = DayData(0, localDate, endDate = localDate)
        val selectedDate: LocalDate = localDate

        calendarViewModel.upsertDayData(selectedDayData).join()


        val getDayData: DayData? = calendarViewModel.monthPeriodData.value.let { it ->

            it.values.lastOrNull {
                it.startDate == localDate
            }

        }

        assert((selectedDate == getDayData?.startDate))
    }

    @Test
    fun `dialogDependOn should return InsertDialog when no events`() = runBlocking {
        val result = calendarViewModel.dialogDependOn(LocalDate.now())
        assertEquals(CalendarDialogPage.InsertDialog, result)
    }

    @Test
    fun `dialogDependOn should return update`() = runBlocking {
        val selectedDayData = DayData(0, localDate, endDate = localDate.plusDays(30))
        val selectedDate: LocalDate = localDate.plusDays(10)

        calendarViewModel.upsertDayData(selectedDayData).join()

        val result = calendarViewModel.dialogDependOn(selectedDate)
        assertEquals(CalendarDialogPage.UpdateDialog, result)
    }

    @Test
    fun `dialogDependOn should return end`() = runBlocking {
        val selectedDayData = DayData(0, localDate, endDate = null)
        val selectedDate: LocalDate = localDate.plusDays(10)

        calendarViewModel.upsertDayData(selectedDayData).join()

        val result = calendarViewModel.dialogDependOn(selectedDate)
        assertEquals(CalendarDialogPage.EndDialog, result)
    }

    @Test
    fun `dialogDependOn should return cancelDialog`() = runBlocking {
        val selectedDayData = DayData(0, localDate, endDate = null)
        val selectedDate: LocalDate = localDate

        calendarViewModel.upsertDayData(selectedDayData).join()
        calendarViewModel.updateUIStateByDate(selectedDate)

        val result = calendarViewModel.dialogDependOn(selectedDate)
        assertEquals(CalendarDialogPage.CancelDialog, result)
    }


    @After
    fun tearDown() {
        mockDatabase.close()
    }
}