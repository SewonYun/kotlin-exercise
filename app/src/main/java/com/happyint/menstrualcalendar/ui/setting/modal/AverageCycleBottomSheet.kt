@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.menstrualcalendar.ui.setting.modal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.entities.user.Information
import com.happyint.menstrualcalendar.util.ViewModelProvider
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun AverageCycleBottomSheet(showIt: MutableState<Boolean>) {

    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    if (showIt.value) {
        ModalBottomSheet(
            onDismissRequest = { showIt.value = false },
            sheetState = bottomSheetState
        ) {

            val userInfoViewModel: UserInfoViewModel = ViewModelProvider.getUserInfoViewModel()

            val averegeCycle by userInfoViewModel.averageCycle.collectAsState(0)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                showIt.value = false
                            }
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.close))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {

                items((1..31).toList()) { it ->

                    val bgColor: ListItemColors = AverageCycleRowBgColor(averegeCycle, it)
                    ListItem(
                        colors = bgColor,
                        modifier = Modifier
                            .clickable {
                                userInfoViewModel.updateUserInfo(
                                    userInformation = Information(
                                        id = 0,
                                        birth = userInfoViewModel.birth.value,
                                        name = userInfoViewModel.name.value,
                                        averageMenstrualCycle = it
                                    )
                                )
                            }
                        ,
                        headlineContent = {
                            Text(stringResource(R.string.day, it))
                        },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }

            }

        }
    }

}
