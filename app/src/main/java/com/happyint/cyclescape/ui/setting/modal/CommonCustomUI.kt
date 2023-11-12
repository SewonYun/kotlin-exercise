package com.happyint.cyclescape.ui.setting.modal

import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun birthRowBgColor(age: String?, ageInModal: Int): ListItemColors {

    val compare = if (age.isNullOrEmpty()) 0 else age.toInt()

    return if (compare == ageInModal) {
        ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    } else {
        ListItemDefaults.colors()
    }
}

@Composable
fun AverageCycleRowBgColor(cycle: Int, cycleInModal: Int): ListItemColors {

    return if (cycle == cycleInModal) {
        ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    } else {
        ListItemDefaults.colors()
    }
}

