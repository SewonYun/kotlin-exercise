package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.happyint.cyclescape.R
import com.happyint.cyclescape.ui.calendar.dialogMenu.DynamicElementList
import com.happyint.cyclescape.util.ViewModelProvider

@ExperimentalMaterial3Api
@Composable
fun UserInputDialog(openDialog: MutableState<Boolean>) {


    val calendarViewModel = ViewModelProvider.getCalendarViewModel()

    if (!openDialog.value) {
        return
    }

    val uiState = calendarViewModel.uiState.collectAsState()
    val selectedDate = uiState.value.selectedDate!!

    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = { Text(text = selectedDate.toString()) },
        text = {

            Column {

                DynamicElementList {
                    openDialog.value = false
                    calendarViewModel.initUIState()
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
