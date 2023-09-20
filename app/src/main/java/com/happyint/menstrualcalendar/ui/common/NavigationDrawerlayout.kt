package com.happyint.menstrualcalendar.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.constants.UserPage

@Composable
fun LeftDrawerLayout(
    drawerState: DrawerState,
    currentScreen: MutableState<UserPage>,
    content: @Composable () -> Unit
) {
    // icons to mimic drawer destinations
    val items: List<NavMenuProperties> = listOf(
        NavMenuProperties(
            name = stringResource(id = R.string.favorite),
            icon = Icons.Default.Favorite,
            cb = { currentScreen.value = UserPage.OPENING }),
        NavMenuProperties(
            name = stringResource(id = R.string.setting),
            icon = Icons.Default.Settings,
            cb = { currentScreen.value = UserPage.SETTING }),
        NavMenuProperties(
            name = stringResource(id = R.string.notice_history),
            icon = Icons.Default.Notifications,
            cb = { currentScreen.value = UserPage.NOTICE })
    )

    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            selectedItem.value = item
                            item.cb()
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = content
    )
}