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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.R
import com.happyint.cyclescape.ui.calendar.dialogMenu.DialogRoot
import com.happyint.cyclescape.ui.calendar.dialogMenu.LittleInputNoteRoot
import com.happyint.cyclescape.viewModels.CalendarViewModel

@ExperimentalMaterial3Api
@Composable
fun UserInputDialog(
    getOpenDialog: () -> MutableState<Boolean>, getOpenLittleNoteDialog: () ->
    MutableState<Boolean>
) {

    val calendarViewModel = viewModel<CalendarViewModel>()
    val openDialog = getOpenDialog()
    val openLittleNoteDialog = getOpenLittleNoteDialog()

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

                LittleInputNoteRoot { openLittleNoteDialog }

                DialogRoot(getOpenLittleNoteDialog) {
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
