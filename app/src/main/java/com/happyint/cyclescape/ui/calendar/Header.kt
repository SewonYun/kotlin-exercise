package com.happyint.cyclescape.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.cyclescape.R
import java.time.YearMonth

@Composable
fun CalendarHeader(month: YearMonth) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = Color.Black,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .defaultMinSize(minWidth = 300.dp, minHeight = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.month_header, month.month.name, month.year))
        }
    }

    Divider(
        color = MaterialTheme.colorScheme.outline,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )

}

@Preview
@Composable
fun PreviewHeader() {
    CalendarHeader(month = YearMonth.now())
}