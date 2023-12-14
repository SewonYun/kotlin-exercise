package com.happyint.cyclescape.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class UserPage(val value: Int) {
    MAIN(0),
    SETTING(1),
    CALENDAR(2),
    NOTICE(3);

    companion object {
        fun getBottomNavIcon(userPage: UserPage): ImageVector {
            return when (userPage) {
                MAIN -> Icons.Filled.Home
                SETTING -> Icons.Filled.Settings
                CALENDAR -> Icons.Filled.DateRange
                NOTICE -> Icons.Filled.Notifications
            }
        }

    }

}