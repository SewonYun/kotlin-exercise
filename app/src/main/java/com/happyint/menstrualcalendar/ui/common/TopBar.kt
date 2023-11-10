@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.happyint.menstrualcalendar.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.style.TextOverflow
import com.happyint.menstrualcalendar.constants.UserPage


@ExperimentalMaterial3Api
@Composable
fun TopBar(currentScreen: MutableState<UserPage>) {
    TopAppBar(
        title = {
            Text(
                "Large TopAppBar",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                currentScreen.value = UserPage.MAIN
            }) {
                if (currentScreen.value == UserPage.MAIN) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )

                }

            }
        }
    )
}