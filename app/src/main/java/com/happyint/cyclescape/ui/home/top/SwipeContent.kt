@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.cyclescape.ui.home.top

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopSwipeContent(cb: () -> Unit) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        content = {
            items(3) { index ->
                Card(
                    onClick = cb,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredWidthIn(max = 200.dp)
                        .padding(10.dp)
                        .height(300.dp)
                ) {
                    Box(
                        Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                            )
                            .fillMaxSize()
                    ) {
                        var bean: CardSurfaceInterface? = null

                        when (index) {
                            0 -> bean = HistoryCard()
                            1 -> bean = WriteCard()
                            2 -> bean = PredictCard()
                        }

                        bean?.DataPreview()
                    }

                }
            }
        },
        horizontalArrangement = Arrangement.SpaceBetween
    )

}


@Preview
@Composable
fun TopSwipeContentPreview() {
    TopSwipeContent {}
}
