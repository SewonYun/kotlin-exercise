@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.menstrualcalendar.ui.home.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.R


@Composable
fun TopSwipeContent(cb: () -> Unit) {
    Card(
        onClick = cb,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(300.dp)
    ) {
        Box(
            Modifier
                .paint(
                    painterResource(id = R.drawable.card_sample1),
                    contentScale = ContentScale.Crop,

                    )
                .fillMaxSize()
        ) {
            Text("Clickable", Modifier.align(Alignment.Center))
        }
    }
}


@Preview
@Composable
fun TopSwipeContentPreview() {
    TopSwipeContent {}
}
