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
import com.happyint.menstrualcalendar.util.ViewModelProvider

@ExperimentalMaterial3Api
@Composable
fun FeatureDialog(openDialog: MutableState<Boolean>) {
    val features = listOf("기능 1", "기능 2", "기능 3", "기능 4")
    val calendarViewModel = ViewModelProvider.getCalendarViewModel()

    if (openDialog.value) {
        val uiState = calendarViewModel.uiState.collectAsState()
        val dayInfo = uiState.value.selectedDate.toString()
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "$dayInfo 기능을 선택하세요") },
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
                    Text("닫기")
                }
            }
        )
    }
}
