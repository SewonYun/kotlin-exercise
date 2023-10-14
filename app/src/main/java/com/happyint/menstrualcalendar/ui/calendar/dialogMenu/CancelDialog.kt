package com.happyint.menstrualcalendar.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.ui.common.ConfirmDialog


@ExperimentalMaterial3Api
@Composable
fun CancelStartDateConfirmDialog(clickStartDateInteraction: ClickStartDateInteraction) {
    ConfirmDialog(
        "Doy u want remove it?",
        "are u sure?",
        stringResource(id = R.string.close),
        stringResource(id = R.string.cancel),
        {
            clickStartDateInteraction.close()
        },
        {
            clickStartDateInteraction.close()
        }
    )
}