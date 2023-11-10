package com.happyint.menstrualcalendar.constants

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import com.happyint.menstrualcalendar.PeriodApplication
import com.happyint.menstrualcalendar.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

enum class UserPage(val value: Int) {
    OPENING(1) {
        @Composable
        override fun GoBack(currentScreen: MutableState<UserPage>) {
        }
    },
    MAIN(2) {

        @Composable
        override fun GoBack(currentScreen: MutableState<UserPage>) {

            val openDialog = remember { mutableStateOf(false) }

            BackHandler {

                if (openDialog.value) {
                    exitProcess(0)
                }

                openDialog.value = true

                Toast.makeText(
                    PeriodApplication.instance,
                    R.string.app_exit_dialog_asking,
                    Toast.LENGTH_SHORT
                ).show()

                GlobalScope.launch(Dispatchers.IO) {
                    delay(3000)
                    openDialog.value = false
                }

            }
        }
    },
    SETTING(3) {
        @Composable
        override fun GoBack(currentScreen: MutableState<UserPage>) {
            BackHandler {
                currentScreen.value = MAIN
            }
        }
    },
    CALENDAR(4) {
        @Composable
        override fun GoBack(currentScreen: MutableState<UserPage>) {
            BackHandler {
                currentScreen.value = MAIN
            }
        }
    },
    NOTICE(5) {
        @Composable
        override fun GoBack(currentScreen: MutableState<UserPage>) {
            BackHandler {
                currentScreen.value = MAIN
            }
        }
    };

    @Composable
    abstract fun GoBack(currentScreen: MutableState<UserPage>)

    companion object {
        fun getBottomNavIcon(userPage: UserPage): ImageVector {
            return when (userPage) {
                MAIN -> Icons.Filled.Home
                OPENING -> Icons.Filled.Favorite
                SETTING -> Icons.Filled.Settings
                NOTICE -> Icons.Filled.Notifications
                CALENDAR -> Icons.Filled.DateRange
            }
        }

    }

}