package com.happyint.cyclescape.ui.calendar.dialogMenu

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.R
import com.happyint.cyclescape.service.calendar.CalendarDialogPage
import com.happyint.cyclescape.viewModels.CalendarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterial3Api
@Composable
fun DialogRoot(getOpenLittleNoteDialog: () -> MutableState<Boolean>, closeCallback: () -> Unit) {
    val calendarViewModel = viewModel<CalendarViewModel>()

    val invalidRemember = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val clickDate = calendarViewModel.uiState.value.selectedDate!!
        invalidRemember.value = calendarViewModel.isInvalidation(clickDate)
    }

    if (invalidRemember.value) {
        AlertIncorrectSelection()
        return
    }

    DynamicElementList(getOpenLittleNoteDialog, closeCallback)
}

@ExperimentalMaterial3Api
@Composable
fun AlertIncorrectSelection() {
    Text(text = stringResource(id = R.string.incorrect_selection_alert))
}

@ExperimentalMaterial3Api
@Composable
fun DynamicElementList(
    getOpenLittleNoteDialog: () -> MutableState<Boolean>, closeCallback: () ->
    Unit
) {

    val lastComponent: @Composable () -> Unit = {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val state = getOpenLittleNoteDialog()
                state.value = true
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.menu_note))
            }
        }
    }

    DialogRendering(closeCallback, lastComponent)


}

@Composable
fun DialogRendering(closeCallback: () -> Unit, lastComponent: @Composable () -> Unit) {
    val calendarViewModel = viewModel<CalendarViewModel>()
    val date = calendarViewModel.uiState.collectAsState().value.selectedDate
    val dayData = calendarViewModel.uiState.collectAsState().value.selectedDayData

    var dialogPage: CalendarDialogPage? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {

        val job = CoroutineScope(Dispatchers.IO).launch {
            dialogPage = calendarViewModel.dialogDependOn(date ?: return@launch)
        }

        onDispose {
            job.cancel()
        }

    }

    dialogPage?.let {

        when (it) {
            CalendarDialogPage.InsertDialog -> StartDateButton(closeCallback)
            CalendarDialogPage.EndDialog -> EndDateButton(closeCallback)
            CalendarDialogPage.CancelDialog -> {
                RemoveDateButton(closeCallback)
                lastComponent()
            }

            CalendarDialogPage.UpdateDialog -> {
                UpdateDateButton(dayData, closeCallback)
                lastComponent()
            }
        }

    }
}
