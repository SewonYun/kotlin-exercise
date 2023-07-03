@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.menstrualcalendar.ui.home.middle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.ui.home.modal.DatePicker
import com.maxkeppeker.sheets.core.models.base.UseCaseState


@Composable
fun MiddleContent(tabIndex: MutableState<Int>) {
    val calendarState = UseCaseState()
    DatePicker(calendarState)
    Card(
        onClick = {
            calendarState.show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(1000.dp)
    ) {
        Box(
            Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            Text("Clickable" + tabIndex.value.toString(), Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun PreviewMiddleContent() {
    MiddleContent(remember { mutableStateOf(0) })
}