package com.happyint.menstrualcalendar.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun ConfirmDialog(
    title: String = "",
    text: String = "",
    confirmText: String = "",
    cancelText: String = "",
    confirmCallBack: () -> Unit,
    closeCallback: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { closeCallback() },
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            Button(onClick = { confirmCallBack() }) {
                Text(confirmText)
            }
        },
        dismissButton = {
            Button(onClick = { closeCallback() }) {
                Text(cancelText)
            }
        }
    )

}