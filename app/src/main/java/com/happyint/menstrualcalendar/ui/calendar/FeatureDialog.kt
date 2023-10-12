package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.ui.calendar.dialogMenu.ClickStartDate
import com.happyint.menstrualcalendar.ui.calendar.dialogMenu.DynamicElement
import com.happyint.menstrualcalendar.util.ViewModelProvider

@ExperimentalMaterial3Api
@Composable
fun FeatureDialog(openDialog: MutableState<Boolean>, clickStartDate: ClickStartDate) {

    val calendarViewModel = ViewModelProvider.getCalendarViewModel()

    if (openDialog.value) {
        val uiState = calendarViewModel.uiState.collectAsState()
        val selectedDate = uiState.value.selectedDate!!

        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = selectedDate.toString()) },
            text = {
                Column {
                    DynamicElement(clickStartDate) {
                        openDialog.value = false
                    }
                }
            },
            confirmButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text(stringResource(id = R.string.close))
                }
            }
        )

    }
}
