@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.constants.UserPage
import kotlinx.coroutines.launch


@Preview
@Composable
fun PreviewTopBar() {
    val currentScreen = remember { mutableStateOf(UserPage.SETTING) }
    TopBar(currentScreen)
}

@Composable
fun TopBar(currentScreen: MutableState<UserPage>) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = {
            Text(
                stringResource(id = R.string.setting),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    currentScreen.value = UserPage.MAIN
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "go back home"
                )
            }
        }
    )
}