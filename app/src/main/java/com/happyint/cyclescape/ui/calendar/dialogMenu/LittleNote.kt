package com.happyint.cyclescape.ui.calendar.dialogMenu

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.R
import com.happyint.cyclescape.constants.Numbers
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteDataBuilder
import com.happyint.cyclescape.viewModels.CalendarViewModel
import com.happyint.cyclescape.viewModels.LittleNoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LittleInputNoteRoot(getOpenDialog: () -> MutableState<Boolean>) {

    val rememberedName = remember { mutableStateOf("") }
    val calendarViewModel = viewModel<CalendarViewModel>()
    val littleNoteViewModel = viewModel<LittleNoteViewModel>()
    val openDialog = getOpenDialog()


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
                LittleNoteInputDialog(rememberedName)
            }

        },
        dismissButton = {

            Button(onClick = { openDialog.value = false }) {
                Text(stringResource(id = R.string.close))
            }

        },
        confirmButton = {
            Button(
                onClick = {

                    CoroutineScope(Dispatchers.IO).launch {


                        val dailyNoteData =
                            littleNoteViewModel.getDailyNoteDataByNoteDate(selectedDate)

                        dailyNoteData.let {

                            if (it == null) {
                                val freshDailyNote =
                                    DailyNoteDataBuilder.getEmptyDailyNoteData(selectedDate)
                                littleNoteViewModel.insert(
                                    freshDailyNote.copy(
                                        content =
                                        rememberedName.value
                                    )
                                )
                                return@let
                            }

                            littleNoteViewModel.insert(
                                it.copy(
                                    content = rememberedName.value
                                )
                            )

                        }
                        openDialog.value = false
                    }

                }
            ) {
                Text(stringResource(id = R.string.confirm))
            }
        }
    )
}

@Composable
fun LittleNoteInputDialog(rememberedName: MutableState<String>) {

    val calendarViewModel = viewModel<CalendarViewModel>()
    val littleNoteViewModel = viewModel<LittleNoteViewModel>()
    val date = calendarViewModel.uiState.collectAsState().value.selectedDate!!

    LaunchedEffect(true) {
        val dailyNoteData =
            littleNoteViewModel.getDailyNoteDataByNoteDate(noteDate = date)

        if (dailyNoteData != null) {
            rememberedName.value = dailyNoteData.content
        }
    }

    val isError = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = AlertDialogDefaults.TonalElevation
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = rememberedName.value,
                minLines = 10,
                maxLines = 10,
                onValueChange = {

                    rememberedName.value = it.take(Numbers.MAX_LITTLE_NOTE_CONTENT_LENGTH.value)
                    isError.value = it.length > Numbers.MAX_LITTLE_NOTE_CONTENT_LENGTH.value

                    if (isError.value) {
                        Toast.makeText(
                            CycleScapeApplication.instance,
                            CycleScapeApplication.instance.getString(
                                R.string
                                    .little_note_max_length_alert, Numbers
                                    .MAX_LITTLE_NOTE_CONTENT_LENGTH.value
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                singleLine = false,
                label = { Text(text = stringResource(id = R.string.menu_note_input_explain)) }
            )
        }

    }
}