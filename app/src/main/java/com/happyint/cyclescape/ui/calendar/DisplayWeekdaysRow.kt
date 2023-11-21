package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happyint.cyclescape.R

@Preview
@Composable
fun DisplayWeekdaysRow() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray)
                    .height(30.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.sun), style = TextStyle(fontSize = 12.sp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray)
                    .height(30.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.mon), style = TextStyle(fontSize = 12.sp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray)
                    .height(30.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.tue), style = TextStyle(fontSize = 12.sp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray)
                    .height(30.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.wed), style = TextStyle(fontSize = 12.sp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray)
                    .height(30.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.thu), style = TextStyle(fontSize = 12.sp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray)
                    .height(30.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.fri), style = TextStyle(fontSize = 12.sp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray)
                    .height(30.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.sat), style = TextStyle(fontSize = 12.sp))
            }

        }
    }
}