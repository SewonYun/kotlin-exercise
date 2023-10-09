package com.happyint.menstrualcalendar.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.customApi.testBorder
import java.time.YearMonth

@Composable
fun DisplayDaysOfMonth(month: YearMonth) {
    val daysInMonth = month.lengthOfMonth()
    val firstDayOfWeek = month.atDay(1).dayOfWeek.value % 7 // 일요일이 0이 되도록 조정

    Surface(modifier = Modifier
        .fillMaxWidth()
        .testBorder()) {
        Column {
            var startDay = 1
            while (startDay <= daysInMonth) {
                Row {
                    for (i in 0..6) {
                        if (startDay == 1 && i < firstDayOfWeek) {
                            // 월의 첫 날이 시작하는 요일까지 빈 공간을 추가합니다.
                            Box(modifier = Modifier
                                .weight(1f)
                                .height(50.dp))
                        } else if (startDay <= daysInMonth) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = startDay.toString())
                            }
                            startDay++
                        } else {
                            // 날짜가 없는 경우 빈 박스를 표시합니다.
                            Box(modifier = Modifier
                                .weight(1f)
                                .height(50.dp))
                        }
                    }
                }
            }
        }
    }
}
