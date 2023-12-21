package com.happyint.cyclescape.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.happyint.cyclescape.constants.UserPage
import com.happyint.cyclescape.constants.UserPage.Companion.getBottomNavIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BottomNavBar(currentPage: MutableState<UserPage>) {

    val items = UserPage.values()

    NavigationBar {
        items.forEachIndexed { _, item ->

            NavigationBarItem(
                icon = {
                    Icon(
                        getBottomNavIcon(item),
                        contentDescription = "",
                        tint = if (item == currentPage.value) Color.Black else Color
                            .LightGray
                    )
                },
                selected = item == currentPage.value,
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        currentPage.value = item
                    }
                }
            )

        }
    }

}
