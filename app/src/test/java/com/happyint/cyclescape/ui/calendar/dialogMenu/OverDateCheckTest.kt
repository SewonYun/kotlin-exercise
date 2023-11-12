package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import java.time.LocalDate

@ExperimentalMaterial3Api
@RunWith(ParameterizedRobolectricTestRunner::class)
class OverDateCheckTest(
    private val localDate: LocalDate, private val expectation: Boolean,
    private val message: () -> String
) {
    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data() = listOf(
            arrayOf(LocalDate.parse("1000-01-01"), false, { "past date test failed" }),
            arrayOf(LocalDate.parse("2500-01-01"), true, { "future date test failed" }),
            arrayOf(LocalDate.now(), false, { "today test failed" })
        )
    }

    @Test
    fun test() {
        assert(OverDateCheck.isOver(localDate) == expectation, message)
    }
}