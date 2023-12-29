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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.R
import com.happyint.cyclescape.constants.Numbers
import com.happyint.cyclescape.viewModels.CalendarViewModel
import com.happyint.cyclescape.viewModels.UserInfoViewModel

@Composable
fun LittleInputNoteRoot(getOpenDialog: () -> MutableState<Boolean>) {

    val calendarViewModel = viewModel<CalendarViewModel>()
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

//                LittleNoteInputDialog {
//                    openDialog.value = false
//                    calendarViewModel.initUIState()
//                }

            }

        },
        confirmButton = {

            Button(onClick = { openDialog.value = false }) {
                Text(stringResource(id = R.string.close))
            }

        }
    )
}

@Preview
@Composable
fun LittleNoteInputDialog() {

    val userInfoViewModel = viewModel<UserInfoViewModel>()
    val localScopeName = userInfoViewModel.information.collectAsState()
        .value
        .name!!

    var rememberedName: String by remember { mutableStateOf(localScopeName) }
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
                value = rememberedName,
                minLines = 10,
                maxLines = 10,
                onValueChange = {

                    rememberedName = it.take(Numbers.MAX_NICKNAME_LENGTH.value)
                    isError.value = it.length > Numbers.MAX_NICKNAME_LENGTH.value

                    if (isError.value) {
                        Toast.makeText(
                            CycleScapeApplication.instance,
                            CycleScapeApplication.instance.getString(R.string.nick_max_length_alert),
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