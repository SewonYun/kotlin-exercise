@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.happyint.cyclescape.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.happyint.cyclescape.R
import com.happyint.cyclescape.constants.AppName
import com.happyint.cyclescape.constants.UserPage


@ExperimentalMaterial3Api
@Composable
fun TopBar(currentScreen: MutableState<UserPage>) {
    val agbalumoFamily = FontFamily(
        Font(R.font.agbalumo, FontWeight.Light),
        Font(R.font.agbalumo, FontWeight.Normal),
        Font(R.font.agbalumo, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.agbalumo, FontWeight.Medium),
        Font(R.font.agbalumo, FontWeight.Bold)
    )

    CenterAlignedTopAppBar(

        title = {
            Text(
                AppName.Pascal.value,
                fontFamily = agbalumoFamily,
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