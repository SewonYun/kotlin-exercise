package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.util.ViewModelProvider

@ExperimentalMaterial3Api
@Composable
fun FeatureDialog(openDialog: MutableState<Boolean>) {
    val features = listOf(
        stringResource(id = R.string.menu_start_date),
        stringResource(id = R.string.menu_note),
        stringResource(id = R.string.menu_end_date)
    )
    val calendarViewModel = ViewModelProvider.getCalendarViewModel()

    if (openDialog.value) {
        val uiState = calendarViewModel.uiState.collectAsState()
        val dayInfo = uiState.value.selectedDate.toString()
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = dayInfo) },
            text = {
                Column {
                    features.forEach { feature ->
                        TextButton(onClick = { /* 기능에 대한 동작을 여기에 추가하세요 */ }) {
                            Text(text = feature)
                        }
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
