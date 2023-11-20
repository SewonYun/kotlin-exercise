package com.happyint.cyclescape.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.R
import com.happyint.cyclescape.service.calendar.CalendarDialogPage
import com.happyint.cyclescape.viewModels.CalendarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun DynamicElementList(closeCallback: () -> Unit) {

    DialogRendering(closeCallback)

    TextButton(onClick = { closeCallback() }) {
        Text(text = stringResource(id = R.string.menu_note))
    }

}

@Composable
fun DialogRendering(closeCallback: () -> Unit) {
    val calendarViewModel = viewModel<CalendarViewModel>()
    val date = calendarViewModel.uiState.collectAsState().value.selectedDate

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
            CalendarDialogPage.CancelDialog -> RemoveDateButton(closeCallback)
        }
    }
}
