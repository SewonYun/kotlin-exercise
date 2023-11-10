package com.happyint.menstrualcalendar.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.happyint.menstrualcalendar.ui.theme.MenstrualCalendarTheme

@Composable
fun Opening(iterationsCount: Int) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset(
            "animation-calendar" +
                    ".json"
        )
    )
    LottieAnimation(composition, iterations = iterationsCount)
}

@Preview(showBackground = true)
@Composable
fun OpeningPreview() {
    MenstrualCalendarTheme {
        Opening(1)
    }
}