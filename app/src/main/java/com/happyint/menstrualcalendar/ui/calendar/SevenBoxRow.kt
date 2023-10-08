package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.customApi.testBorder

@Composable
fun SevenBoxRow() {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .testBorder()) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.14f)
                    .testBorder()
                    .height(30.dp)
            ) {
                Text(text = "112213")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.14f)
                    .testBorder()
                    .height(30.dp)
            ) {
                Text(text = "112213")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.14f)
                    .testBorder()
                    .height(30.dp)
            ) {
                Text(text = "112213")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.14f)
                    .testBorder()
                    .height(30.dp)
            ) {
                Text(text = "112213")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.14f)
                    .testBorder()
                    .height(30.dp)
            ) {
                Text(text = "112213")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.14f)
                    .testBorder()
                    .height(30.dp)
            ) {
                Text(text = "112213")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.14f)
                    .testBorder()
                    .height(30.dp)
            ) {
                Text(text = "112213")
            }

        }
    }
}