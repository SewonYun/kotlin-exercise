package com.happyint.menstrualcalendar.ui.calendar.dialogMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.happyint.menstrualcalendar.R

@ExperimentalMaterial3Api
@Composable
fun DynamicElement(
    clickStartDate: ClickStartDate,
    closeCallback: () -> Unit
) {

    TextButton(onClick = {
        clickStartDate.insertOrRemove()
        closeCallback()
    }) {
        Text(text = stringResource(id = R.string.menu_start_date))
    }


    TextButton(onClick = { closeCallback() }) {
        Text(text = stringResource(id = R.string.menu_end_date))
    }

    TextButton(onClick = { closeCallback() }) {
        Text(text = stringResource(id = R.string.menu_note))
    }

}