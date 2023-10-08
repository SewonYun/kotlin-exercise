package com.happyint.menstrualcalendar.ui.home

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.happyint.menstrualcalendar.R
import kotlin.system.exitProcess

@ExperimentalMaterial3Api
@Composable
fun ConfirmExitDialog(openDialog: MutableState<Boolean>) {

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(stringResource(id = R.string.app_exit_dialog_asking)) },
            text = { Text(stringResource(id = R.string.app_exit_button_explain)) },
            confirmButton = {
                Button(onClick = { exitProcess(0) }) {
                    Text(stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }
}