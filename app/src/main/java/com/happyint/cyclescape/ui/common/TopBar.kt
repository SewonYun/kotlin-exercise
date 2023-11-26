@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.happyint.cyclescape.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.constants.AppName
import com.happyint.cyclescape.customApi.testBorder
import com.happyint.cyclescape.service.calendar.TopbarSyncDelegator
import com.happyint.cyclescape.ui.graphics.agbalumoFamily
import com.happyint.cyclescape.viewModels.CalendarViewModel


@ExperimentalMaterial3Api
@Composable
fun TopBar() {

    CenterAlignedTopAppBar(

        title = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(0.5f)
                        .testBorder(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AppName.Pascal.value,
                        fontFamily = agbalumoFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 30.sp, color = Color.Black)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                val calendarViewModel = viewModel<CalendarViewModel>()
                val month = calendarViewModel.uiState.collectAsState().value.month

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {


                    if (month != null) {
                        TopbarSyncDelegator.Grid(month)
                    }

                }

            }
        }
    )
}