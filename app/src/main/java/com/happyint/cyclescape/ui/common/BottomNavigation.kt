package com.happyint.cyclescape.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.happyint.cyclescape.constants.UserPage
import com.happyint.cyclescape.constants.UserPage.Companion.getBottomNavIcon

@Composable
fun BottomNavBar(currentScreen: MutableState<UserPage>) {

    val items = UserPage.values().filter { it != UserPage.OPENING }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(getBottomNavIcon(item), contentDescription = "") },
                selected = item.toString() == currentScreen.value.toString(),
                onClick = { currentScreen.value = item }
            )
        }
    }

}
