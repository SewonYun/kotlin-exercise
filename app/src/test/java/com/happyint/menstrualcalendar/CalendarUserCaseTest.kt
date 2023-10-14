package com.happyint.menstrualcalendar

import androidx.compose.material3.ExperimentalMaterial3Api
import com.happyint.menstrualcalendar.ui.calendar.dialogMenu.ClickStartDateInteraction
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class CalendarUserCaseTest {
    @OptIn(ExperimentalMaterial3Api::class)
    private val clickStartDateInteraction: ClickStartDateInteraction = mock()

    @OptIn(ExperimentalMaterial3Api::class)
    @Before
    fun createMock() {
    }

    @Test
    fun test() = runBlocking {

    }
}