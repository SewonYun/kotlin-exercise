package com.happyint.cyclescape.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.happyint.cyclescape.constants.UserPage
import com.happyint.cyclescape.constants.UserPage.Companion.getBottomNavIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavBar(currentScreen: MutableState<UserPage>, pagerState: PagerState) {

    val items = UserPage.values()

    NavigationBar {
        items.forEachIndexed { _, item ->
            NavigationBarItem(
                icon = { Icon(getBottomNavIcon(item), contentDescription = "") },
                selected = item.toString() == currentScreen.value.toString(),
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        pagerState.scrollToPage(item.ordinal)
                    }
                }
            )
        }
    }

}
