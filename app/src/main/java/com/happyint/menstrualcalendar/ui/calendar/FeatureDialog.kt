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
import com.happyint.menstrualcalendar.entities.calendar.enums.DialogMenu
import com.happyint.menstrualcalendar.util.ViewModelProvider

@ExperimentalMaterial3Api
@Composable
fun FeatureDialog(openDialog: MutableState<Boolean>) {

    val calendarViewModel = ViewModelProvider.getCalendarViewModel()

    if (openDialog.value) {
        val uiState = calendarViewModel.uiState.collectAsState()
        val dayInfo = uiState.value.selectedDate.toString()
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = dayInfo) },
            text = {
                Column {
                    DialogMenu.values().forEach { feature ->
                        TextButton(onClick = { feature.nextPhase() }) {
                            Text(text = feature.toString())
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
