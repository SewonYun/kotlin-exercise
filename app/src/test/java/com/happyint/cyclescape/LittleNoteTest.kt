package com.happyint.cyclescape


import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteDataBuilder
import com.happyint.cyclescape.repositories.LittleNoteRepository
import com.happyint.cyclescape.viewModels.LittleNoteViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.time.LocalDate

@RunWith(RobolectricTestRunner::class)
class LittleNoteTest {

    private lateinit var mockDatabase: AppDatabase

    private lateinit var littleNoteRepository: LittleNoteRepository

    private lateinit var littleNoteViewModel: LittleNoteViewModel

    @Before
    fun setUp() {
        mockDatabase = getRoomInMemoryAppDatabase()
        littleNoteRepository = LittleNoteRepository(mockDatabase.littleNoteDao())
        littleNoteViewModel = LittleNoteViewModel(littleNoteRepository = littleNoteRepository)
    }

    @Test
    fun `fetchLittleNote should emit a map of note date and daily note data`() = runTest {
        // given
        val noteDate1 = LocalDate.of(2024, 1, 1)
        val noteDate2 = LocalDate.of(2024, 1, 2)
        val dailyNoteData1 = DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate1)
        val dailyNoteData2 = DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate2)
        val littleNoteList = listOf(dailyNoteData1, dailyNoteData2)
        littleNoteList.forEach {
            littleNoteViewModel.insert(it)
        }
        val expectedMap = mutableMapOf(
            noteDate2.toString() to dailyNoteData2.copy(id = 2),
            noteDate1.toString() to dailyNoteData1.copy(id = 1),
        )

        // when
        littleNoteViewModel.fetchLittleNote().join()

        // then
        val actualMap = littleNoteViewModel.littleNoteData.first()
        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `fetchDailyData should emit an empty daily note data if the given note date is not found`() =
        runTest {
            // given
            val noteDate = LocalDate.of(2024, 1, 1)
            val dailyNoteData = DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate)

            // when
            littleNoteViewModel.insert(dailyNoteData).join()
            littleNoteViewModel.fetchDailyData(noteDate)

            // then
            val actualData = littleNoteViewModel.dailyNoteData.first()
            assertEquals(dailyNoteData.noteDate, actualData!!.noteDate)
        }

    @Test
    fun `delete should delete the given daily note data and update the state flows`() = runTest {
        // given
        val noteDate = LocalDate.of(2024, 1, 1)
        val dailyNoteData = DailyNoteDataBuilder.getEmptyDailyNoteData(noteDate)
        littleNoteViewModel.insert(dailyNoteData).join()
        val dbDailyNoteData = littleNoteViewModel.dailyNoteData.value!!

        // when
        littleNoteViewModel.delete(dbDailyNoteData).join()

        // then
        val actualData = littleNoteViewModel.dailyNoteData.first()
        val actualMap = littleNoteViewModel.littleNoteData.first()

        assertEquals(null, actualData)
        assertEquals(mapOf<String, DailyNoteData>(), actualMap)
    }
}
